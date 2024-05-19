import { Component } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { LoginUserService } from '../../model/security/login-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent {

  constructor(loginUserService:LoginUserService, router:Router) {
    if(!loginUserService.isActivated()) {
      router.navigate(['/change-pass'])
    }
  }
}
