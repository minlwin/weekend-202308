import { Injectable, computed, effect, signal } from '@angular/core';
import { LoginUser } from '../login-user';

const LOGIN_USER = 'com.jdc.balance.login.user'

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {

  loginUser = signal<LoginUser | undefined>(undefined)
  isLogin = computed(() => this.loginUser() != undefined)
  isActivated = computed(() => this.isLogin() && this.loginUser()?.role != "Applied")
  isManagementUser = computed(() => this.isLogin() && (this.loginUser()?.role == 'Admin' || this.loginUser()?.role == 'Manager'))

  constructor() {
    effect(() => {
      try {
        const loginUserData = this.loginUser()
        if(loginUserData) {
          localStorage.setItem(LOGIN_USER, JSON.stringify(loginUserData))
        } else {
          localStorage.removeItem(LOGIN_USER)
        }
      } catch(e) {}
    })

    try {
      var loginUserData = localStorage.getItem(LOGIN_USER)
      if(loginUserData) {
        this.loginUser.set(JSON.parse(loginUserData))
      }
    } catch(e) {}
  }

  setRole(newRole:string) {
    if(this.loginUser()) {
      const {role, ... others} = this.loginUser()!
      const newUser = {role: newRole, ... others}
      this.loginUser.set(newUser)
    }
  }

  logout() {
    this.loginUser.set(undefined)
  }
}
