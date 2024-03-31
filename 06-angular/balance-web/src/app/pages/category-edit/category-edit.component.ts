import { Component, computed, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BALANCE_TYPES } from '../../model/balance-model';

@Component({
  selector: 'app-category-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './category-edit.component.html',
  styles: ``
})
export class CategoryEditComponent {

  edit = input<boolean>(false)
  icon = computed(() => this.edit() ? 'bi-pencil' : 'bi-plus-lg')
  title = computed(() => this.edit() ? 'Edit Category' : 'Add New Category')

  types = signal<string[]>(BALANCE_TYPES)

  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      id: 0,
      name: ['', Validators.required],
      type: ['', Validators.required],
      description: ''
    })
  }

  save() {

  }
}
