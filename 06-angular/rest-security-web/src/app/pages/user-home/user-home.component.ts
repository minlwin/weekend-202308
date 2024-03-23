import { Component, signal } from '@angular/core';
import { SecurityService } from '../../services/security.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-home',
  standalone: true,
  imports: [],
  templateUrl: './user-home.component.html',
  styles: ``
})
export class UserHomeComponent {

  loginUser = signal<any>(undefined)

  constructor(security:SecurityService, router:Router) {
    if(!security.loginUser) {
      router.navigate(['/login'])
    } else {
      this.loginUser.set(security.loginUser)
    }
  }
}
