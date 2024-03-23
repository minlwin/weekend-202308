import { Component, effect, input } from '@angular/core';

@Component({
  selector: 'app-employee-info-list',
  templateUrl: './employee-info-list.component.html',
  styles: ``
})
export class EmployeeInfoListComponent {

  employees = input.required<any[]>()
  showDepartment = input<boolean>(true)
  showPosition = input<boolean>(true)

  constructor() {
    effect(() => {
      console.log(`Length of employee is ${this.employees().length}`)
    })
  }
}
