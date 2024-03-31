import { Component, computed, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ROLES } from '../../model/balance-model';

@Component({
  selector: 'app-employee-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './employee-edit.component.html',
  styles: ``
})
export class EmployeeEditComponent {

  id = input<any>()

  edit = computed<boolean>(() => this.id() != undefined)
  icon = computed<string>(() => this.edit() ? 'bi-pencil' : 'bi-plus-lg')
  title = computed<string>(() => `${this.edit() ? 'Edit' : 'Add New'} Employee`)

  roles = signal<string[]>(ROLES)

  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      id: 0,
      name: ['', Validators.required],
      role: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required]
    })
  }

  save() {

  }

}
