import { Component } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginUserService } from '../../model/security/login-user.service';
import { TokenService } from '../../model/services/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styles: ``
})
export class LoginComponent {

  form:FormGroup

  constructor(builder:FormBuilder, private loginUserService:LoginUserService, private tokenService:TokenService, private router:Router) {
    this.form = builder.group({
      loginId: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login() {
    this.tokenService.generate(this.form.value).subscribe(result => {
      this.loginUserService.loginUser.set(result)
      this.router.navigate(['/home'])
    })
  }
}
