import { Component, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { DepartmentService } from '../../../service/department.service';
import { DepartmentInfoComponent } from './department-info/department-info.component';
import { DepartmentEmployeeComponent } from './department-employee/department-employee.component';
import { WidgetsModule } from '../../../widgets/widgets.module';

@Component({
  selector: 'app-department-details',
  standalone: true,
  imports: [
    WidgetsModule,
    DepartmentInfoComponent,
    DepartmentEmployeeComponent,
    RouterLink],
  templateUrl: './department-details.component.html',
  styles: ``
})
export class DepartmentDetailsComponent {

  data = signal<any>({})

  constructor(route:ActivatedRoute, service:DepartmentService) {
    route.queryParamMap.subscribe(params => {
      const code = params.get('code')

      if(code) {
        service.findById(code).subscribe(result => this.data.set(result.payload))
      }
    })
  }
}
