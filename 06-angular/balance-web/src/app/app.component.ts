import { AfterViewInit, Component, ErrorHandler, Inject, ViewChild, computed, signal, viewChild } from '@angular/core';
import { NavigationEnd, NavigationStart, Router, RouterOutlet } from '@angular/router';
import { WidgetsModule } from './widgets/widgets.module';
import { LoginUserService } from './model/security/login-user.service';
import { ErrorDialogComponent } from './widgets/error-dialog/error-dialog.component';
import { GlobalErrorHandler } from './model/errors/global-error-handler';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WidgetsModule],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent implements AfterViewInit{

  show = signal<boolean>(true)
  isLogin = computed(() => this.loginUserService.isLogin())
  isActivated = computed(() => this.loginUserService.isActivated())

  @ViewChild(ErrorDialogComponent)
  errorDialog?:ErrorDialogComponent

  constructor(private router:Router,
    private loginUserService:LoginUserService,
    @Inject(ErrorHandler) private errorHandler:GlobalErrorHandler
  ) {
    router.events.subscribe(event => {

      if(event instanceof NavigationStart) {
        this.errorDialog?.hideDialog()
      }

      if(event instanceof NavigationEnd) {
        if(event.url != '/login' && !loginUserService.isLogin()) {
          router.navigate(['/login'])
        }


      }
    })
  }

  ngAfterViewInit(): void {
    this.errorHandler.errorDialog = this.errorDialog
  }

  toggleSideBar() {
    this.show.update(state => !state)
  }

  signOut() {
    this.loginUserService.logout()
    this.router.navigate(['/login'])
  }

  onError(logout:boolean) {
    if(logout) {
      this.signOut()
    }
  }
}
