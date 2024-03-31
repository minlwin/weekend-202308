import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { BALANCE_TYPES } from '../../model/balance-types';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [WidgetsModule, RouterLink, ReactiveFormsModule],
  templateUrl: './category.component.html',
  styles: ``
})
export class CategoryComponent {

  form:FormGroup

  types = signal(BALANCE_TYPES)
  list = signal<any[]>([])

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      type: '',
      name: ''
    })
  }

  search() {

  }
}
