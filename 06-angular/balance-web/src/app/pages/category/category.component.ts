import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { BALANCE_TYPES } from '../../model/balance-model';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CategoryService } from '../../model/services/category.service';

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

  constructor(builder:FormBuilder, private service:CategoryService) {
    this.form = builder.group({
      type: '',
      name: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      this.list.set(result)
    })
  }

  upload(file:FileList | null) {
    if(file && file.length > 0) {
      this.service.upload(file[0])
        .subscribe(_ => this.search())
    }
  }
}
