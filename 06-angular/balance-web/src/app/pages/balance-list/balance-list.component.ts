import { Component, computed, effect, input } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';

@Component({
  selector: 'app-balance-list',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './balance-list.component.html',
  styles: ``
})
export class BalanceListComponent {

  type = input<string>()
  icon = computed<string>(() => this.type() == 'Income' ? 'bi-cash-stack' : 'bi-cart-dash')

  constructor() {
  }
}
