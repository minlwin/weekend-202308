import { Component } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginUserService } from '../../model/security/login-user.service';
import { ChangePasswordService } from '../../model/services/change-password.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './change-password.component.html',
  styles: ``
})
export class ChangePasswordComponent {

  form:FormGroup

  constructor(builder:FormBuilder,
    private router:Router,
    private service:ChangePasswordService,
    private loginUserService:LoginUserService) {
    this.form = builder.group({
      loginId: ['', Validators.required],
      oldPassword: ['', Validators.required],
      newPassword: ['', Validators.required]
    })

    if(loginUserService.loginUser()) {
      this.form.patchValue({loginId: loginUserService.loginUser()?.loginId})
    }
  }

  changePassword() {
    if(this.form.valid) {
      this.service.changePassword(this.form.value).subscribe(result => {
        this.loginUserService.setRole(result.role)
        this.router.navigate(['/home'])
      })
    }
  }

}
