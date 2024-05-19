import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginUserService } from './login-user.service';
import { catchError, switchMap, throwError } from 'rxjs';
import { TokenService } from '../services/token.service';
import { url } from 'inspector';

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
      if(error.status == 408) {
        if(loginUserService.isLogin() && !request.url.includes('/token')) {
          return tokenService.refresh({refreshToken: loginUserService.loginUser()?.refreshToken}).pipe(
            switchMap(result => {
              loginUserService.loginUser.set(result)
              return next(req.clone({headers: req.headers.append('Authorization', result.accessToken)}))
            }),
            catchError(error => {
              return throwError(() => error)
            })
          )
        }
      }
      return throwError(() => error)
    })
  );
};
