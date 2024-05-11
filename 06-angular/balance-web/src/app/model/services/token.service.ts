import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { LoginUser } from "../login-user";

const BASE_URL = `${environment.baseUrl}/token`

@Injectable({providedIn: 'root'})
export class TokenService {

  constructor(private http:HttpClient) {}

  generate(form:any) {
    return this.http.post<LoginUser>(`${BASE_URL}/generate`, null, {params: form})
  }

  refresh(form:any) {
    return this.http.post<LoginUser>(`${BASE_URL}/refresh`, null, {params: form})
  }
}
