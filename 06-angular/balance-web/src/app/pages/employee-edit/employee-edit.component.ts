import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ROLES } from '../../model/balance-model';
import { EmployeeService } from '../../model/services/employee.service';
import { Router } from '@angular/router';

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

  constructor(builder:FormBuilder, private service:EmployeeService, private router:Router) {
    this.form = builder.group({
      name: ['', Validators.required],
      role: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required]
    })

    effect(() => {
      const idValue = this.id()

      if(idValue) {
        service.findByIdForEdit(idValue).subscribe(result => {
          this.form.patchValue(result)
        })
      }
    })
  }

  save() {
    if(this.form.valid) {
      const request = this.edit() ? this.service.update(this.id()!, this.form.value) :
        this.service.create(this.form.value)

      request.subscribe(result => {
        this.router.navigate(['/employee', 'details'], {queryParams: {id: result.id}})
      })
    }
  }

}
