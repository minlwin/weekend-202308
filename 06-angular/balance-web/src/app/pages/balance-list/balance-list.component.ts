import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-balance-list',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './balance-list.component.html',
  styles: ``
})
export class BalanceListComponent {

  type = input<string>()
  icon = computed<string>(() => this.type() == 'Income' ? 'bi-cash-stack' : 'bi-cart-dash')

  form:FormGroup
  list = signal<any[]>([])

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      type: '',
      from: '',
      to: '',
      keyword: ''
    })

    effect(() => {
      this.form.patchValue({type: this.type()})
    })
  }

  search() {

  }
}
