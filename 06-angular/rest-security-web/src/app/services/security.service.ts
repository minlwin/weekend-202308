import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  loginUser:LoginUser | undefined

  constructor() { }

}

export interface LoginUser {
  username:string
  token:string
}
