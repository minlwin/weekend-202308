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
    if(login) {
      this.login.emit({title: title, messages: messages, logout: login})
      return
    }

    this.displayMessage(title, messages)
  }

  displayMessage(title:string, messages:string[]) {
    this.title = title
    this.messages = messages
    this.show = true
  }

  hideDialog() {
    this.show = false
  }
}
