import { Component, computed, input } from '@angular/core';
import { LoginUserService } from '../../model/security/login-user.service';

@Component({
  selector: 'app-page-title',
  templateUrl: './page-title.component.html',
  styles: ``
})
export class PageTitleComponent {

  icon = input.required<string>()
  title = input.required<string>()
  username = computed(() => this.loginUser.loginUser()?.name || 'User Name')

  constructor(private loginUser:LoginUserService) {}
}
