import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ROLES, STATUSES } from '../../model/balance-model';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './employee.component.html',
  styles: ``
})
export class EmployeeComponent {

  form:FormGroup

  list = signal<any[]>([])
  roles = signal<string[]>(ROLES)
  statuses = signal<string[]>(STATUSES)

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      role: '',
      keyword: ''
    })
  }

  search() {

  }
}
