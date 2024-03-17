import { Component, input } from '@angular/core';

@Component({
  selector: 'app-department-info',
  standalone: true,
  imports: [],
  templateUrl: './department-info.component.html',
  styles: ``
})
export class DepartmentInfoComponent {

  data = input.required<any>()
}
