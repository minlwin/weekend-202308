import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styles: ``
})
export class ErrorDialogComponent {

  title:string = ''
  messages:string[] = []
  @Output()
  login = new EventEmitter
  show:boolean = false

  showDialog(title:string, messages:string[], login:boolean) {
    this.title = title
    this.messages = messages
    this.show = true
    this.login.emit(login)
  }

  hideDialog() {
    this.show = false
  }
}
