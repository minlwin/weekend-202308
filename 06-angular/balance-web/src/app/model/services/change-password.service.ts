import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";

const API = `${environment.baseUrl}/change-password`

@Injectable({providedIn: 'root'})
export class ChangePasswordService {

  constructor(private http:HttpClient) {
  }

  changePassword(form:any) {
    return this.http.post<any>(API, form)
  }
}
