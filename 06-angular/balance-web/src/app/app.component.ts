import { Component, computed, signal } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { WidgetsModule } from './widgets/widgets.module';
import { LoginUserService } from './model/security/login-user.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WidgetsModule],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent {

  show = signal<boolean>(true)
  isLogin = computed(() => this.loginUserService.loginUser() != undefined)

  constructor(private router:Router, private loginUserService:LoginUserService) {
    router.events.subscribe(event => {
      if(event instanceof NavigationEnd) {
        if(event.url != '/login' && loginUserService.loginUser() == undefined) {
          router.navigate(['/login'])
        }
      }
    })
  }

  toggleSideBar() {
    this.show.update(state => !state)
  }

  signOut() {
    this.loginUserService.logout()
    this.router.navigate(['/login'])
  }
}
