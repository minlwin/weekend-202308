import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginUserService } from './login-user.service';
import { catchError, switchMap, throwError } from 'rxjs';
import { TokenService } from '../services/token.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  let request = req
  const loginUserService = inject(LoginUserService)
  const tokenService = inject(TokenService)

  if(loginUserService.isLogin()) {
    if(!req.url.includes('/token')) {
      request = req.clone({headers: req.headers.append('Authorization', loginUserService.loginUser()!.accessToken)})
    }
  }

  return next(request).pipe(
    catchError(error => {
      if(error instanceof HttpErrorResponse
        && error.status == 408
        && !request.url.includes('token')
        && loginUserService.isLogin()
      ) {
        return tokenService.refresh({refreshToken: loginUserService.loginUser()?.refreshToken}).pipe(
          switchMap(result => {
            loginUserService.loginUser.set(result)
            return next(req.clone({headers: req.headers.append('Authorization', result.accessToken)}))
          })
        )
      }
      return throwError(() => error)
    })
  );
};
