import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DepartmentService } from '../../../service/department.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-department-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './department-edit.component.html',
  styles: ``
})
export class DepartmentEditComponent {

  form:FormGroup

  edit = signal(false)

  constructor(builder:FormBuilder,
    route:ActivatedRoute,
    private router:Router,
    private service:DepartmentService) {
    this.form = builder.group({
      code: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]],
      name: ['', Validators.required],
      phone: ['', Validators.required],
      description: '',
    })

    route.queryParamMap.subscribe(params => {
      const code = params.get('code')

      if(code) {
        this.edit.set(true)
        service.findById(code).subscribe(result => {
          const {hodCode, hodName, hodPhone, ... editForm} = result.payload
          this.form.patchValue(editForm)
        })
      }
    })
  }

  save() {
    if(this.form.valid) {

      const request = this.edit() ? this.service.update(this.form.value) :
        this.service.create(this.form.value)

      request.subscribe(
        result => {
          if(result.success) {
            this.router.navigate(['/departments', 'details'], {queryParams: {code: result.payload.id}})
          }
        }
      )
    }
  }
}
