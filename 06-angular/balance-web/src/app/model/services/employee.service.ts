import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { PageResult } from "../balance-model";

const BASE_URL = `${environment.baseUrl}/employee`

@Injectable({providedIn: 'root'})
export class EmployeeService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<PageResult>(BASE_URL, {params: form})
  }

  findById(id: number) {
    return this.http.get<any>(`${BASE_URL}/${id}`)
  }

  findByIdForEdit(id:number) {
    return this.http.get<any>(`${BASE_URL}/${id}/edit`)
  }

  create(form:any) {
    return this.http.post<any>(BASE_URL, form)
  }

  update(id:number, form:any) {
    return this.http.put<any>(`${BASE_URL}/${id}`, form)
  }

  updateStatus(id:number, form:any) {
    return this.http.put<any>(`${BASE_URL}/${id}/status`, form)
  }
}
