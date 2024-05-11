import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BALANCE_TYPES } from '../../model/balance-model';
import { CategoryService } from '../../model/services/category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './category-edit.component.html',
  styles: ``
})
export class CategoryEditComponent {

  id = input<number>()
  edit = computed<boolean>(() => this.id() != undefined)
  icon = computed(() => this.edit() ? 'bi-pencil' : 'bi-plus-lg')
  title = computed(() => this.edit() ? 'Edit Category' : 'Add New Category')

  types = signal<string[]>(BALANCE_TYPES)

  form:FormGroup

  constructor(builder:FormBuilder, private service:CategoryService, private router:Router) {
    this.form = builder.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      description: ''
    })

    effect(() => {
      const idValue = this.id()
      if(idValue) {
        service.findById(idValue).subscribe(result => {
          const {id, ...formValue} = result
          this.form.patchValue(formValue)
        })
      }
    })
  }

  save() {
    if(this.form.valid) {
      const request = this.edit() ? this.service.update(this.id()!, this.form.value) :
        this.service.create(this.form.value)

      request.subscribe(_ => {
        this.router.navigate(['/category', 'list'])
      })
    }
  }
}
