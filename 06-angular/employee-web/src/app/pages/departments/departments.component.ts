import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { DepartmentService } from '../../service/department.service';

@Component({
  selector: 'app-departments',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './departments.component.html',
  styles: ``
})
export class DepartmentsComponent {

  form:FormGroup
  list = signal<any[]>([])

  constructor(builder:FormBuilder, private service:DepartmentService) {
    this.form = builder.group({
      code: '',
      name: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      this.list.set(result.payload)
    })
  }
}
