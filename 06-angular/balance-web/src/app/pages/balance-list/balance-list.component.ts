import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { LedgerEntryService } from '../../model/services/ledger-entry.service';
import { LoginUserService } from '../../model/security/login-user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-balance-list',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './balance-list.component.html',
  styles: ``
})
export class BalanceListComponent {

  type = input<string>()
  icon = computed<string>(() => this.type() == 'Income' ? 'bi-cash-stack' : 'bi-cart-dash')

  form:FormGroup
  list = signal<any[]>([])

  constructor(builder:FormBuilder, private service:LedgerEntryService, private loginUserService:LoginUserService) {
    this.form = builder.group({
      type: '',
      from: '',
      to: '',
      keyword: ''
    })

    effect(() => {
      if(loginUserService.isEmployee()) {
        this.form.patchValue({loginId: loginUserService.loginUser()?.loginId})
      }

      if(this.type()) {
        this.form.patchValue({type: this.type()})
        this.search()
      }

    }, {allowSignalWrites: true})
  }

  search() {
    console.log('Ledger Entry Search', this.form.value)
    this.service.search(this.form.value).subscribe(result => {
      const {content, ... pageInfo} = result
      this.list.set(content)
    })
  }
}
