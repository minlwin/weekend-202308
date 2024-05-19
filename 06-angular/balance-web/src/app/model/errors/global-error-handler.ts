import { HttpErrorResponse } from "@angular/common/http";
import { ErrorHandler, Injectable, NgZone } from "@angular/core";
import { ErrorDialogComponent } from "../../widgets/error-dialog/error-dialog.component";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  errorDialog?:ErrorDialogComponent

  constructor(private zone:NgZone) {}

  handleError(error: any): void {

    let errors = ['Error from UI, Please contact to development team.']
    let title = "Application Error"
    let login = false

    if(error instanceof HttpErrorResponse) {
      errors = error.error
      title = "Server Error"
      login = false

      if(error.status == 401) {
        title = 'Authentication Error'
        login = true
      } else if(error.status == 403) {
        title = 'Authorization Error'
      }

    }
    console.log("Error Handler", error)
    this.zone.run(() => {
      this.errorDialog?.showDialog(title, errors, login)
    })
  }
}
