import { ApplicationConfig, ErrorHandler } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { tokenInterceptor } from './model/security/token.interceptor';
import { GlobalErrorHandler } from './model/errors/global-error-handler';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, withComponentInputBinding()),
    provideHttpClient(
      withFetch(),
      withInterceptors([tokenInterceptor])
    ),
    provideClientHydration(),
    {provide: ErrorHandler, useClass: GlobalErrorHandler}
  ]
};
