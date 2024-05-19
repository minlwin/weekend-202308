import { Component, effect, input, signal } from '@angular/core';
import { LedgerEntryService } from '../../model/services/ledger-entry.service';
import { WidgetsModule } from '../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-balance-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './balance-details.component.html',
  styles: ``
})
export class BalanceDetailsComponent {

  type = input<string>()
  id = input<number>()
  dto = signal<any>(undefined)

  constructor(service:LedgerEntryService) {
    effect(() => {
      const idValue = this.id()
      if(idValue) {
        service.findById(idValue).subscribe(result => {
          this.dto.set(result)
        })
      }
    }, {allowSignalWrites: true})
  }
}
