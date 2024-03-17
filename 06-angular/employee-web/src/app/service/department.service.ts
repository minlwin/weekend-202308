import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiResponse } from './api-response';
import { environment } from '../../environments/environment';

const BASE_API = `${environment.baseApi}/department`

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<ApiResponse>(BASE_API, {params: form})
  }

  create(form:any) {
    return this.http.post<ApiResponse>(BASE_API, form)
  }

  update(form:any) {
    const {code, ...updateForm} = form
    return this.http.put<ApiResponse>(`${BASE_API}/${code}`, updateForm)
  }

  findById(code:string) {
    return this.http.get<ApiResponse>(`${BASE_API}/${code}`)
  }
}
