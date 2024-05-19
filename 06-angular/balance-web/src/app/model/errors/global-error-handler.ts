import { HttpErrorResponse } from "@angular/common/http";
import { ErrorHandler, Injectable, NgZone } from "@angular/core";
import { ErrorDialogComponent } from "../../widgets/error-dialog/error-dialog.component";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  errorDialog?:ErrorDialogComponent

  constructor(private zone:NgZone) {}

  handleError(error: any): void {
    if(error instanceof HttpErrorResponse) {
      let errors = error.error
      let title = "Error Message"
      let login = false

      if(error.status == 401) {
        title = 'Authentication Error'
        login = true
      } else if(error.status == 403) {
        title = 'Authorization Error'
      }

      this.zone.run(() => {
        this.errorDialog?.showDialog(title, errors, login)
      })
    }

    console.log(error)
  }
}
