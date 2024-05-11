import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginUserService } from './login-user.service';
import { catchError, throwError } from 'rxjs';
import { TokenService } from '../services/token.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  let request = req
  const loginUserService = inject(LoginUserService)
  const tokenService = inject(TokenService)

  const loginUser = loginUserService.loginUser()

  if(loginUser) {
    if(!req.url.includes('/token')) {
      request = req.clone({headers: req.headers.append('Authorization', loginUser.accessToken)})
    }
  }

  return next(request).pipe(
    catchError(error => {
      if(error.status == 401 ) {
        if(loginUser && !req.url.includes('/token')) {
          tokenService.refresh({refreshToken: loginUser.refreshToken}).subscribe(result => {
            loginUserService.loginUser.set(result)

            return next(request)
          })
        }

        loginUserService.loginUser.set(undefined)
      }
      return throwError(() => error)
    })
  );
};
