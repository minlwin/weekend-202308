import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-balance-report',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './balance-report.component.html',
  styles: ``
})
export class BalanceReportComponent {

  form:FormGroup
  list = signal<any[]>([])

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      from : '',
      to: ''
    })
  }

  search() {

  }

}
