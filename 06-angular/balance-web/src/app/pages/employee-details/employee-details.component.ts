import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
import { ChangeHistoryComponent } from './change-history/change-history.component';
import { RouterLink } from '@angular/router';
import { EmployeeService } from '../../model/services/employee.service';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [WidgetsModule, PersonalInfoComponent, ChangeHistoryComponent, RouterLink],
  templateUrl: './employee-details.component.html',
  styles: ``
})
export class EmployeeDetailsComponent {

  id = input<number>()
  personalInfo = signal<any>(undefined)
  changeHistory = signal<any[]>([])

  constructor(service:EmployeeService) {
    effect(() => {
      const idValue = this.id()

      if(idValue) {
        service.findById(idValue).subscribe(result => {
          this.personalInfo.set(result.info)
          this.changeHistory.set(result.history || [])
        })
      }
    })
  }
}
