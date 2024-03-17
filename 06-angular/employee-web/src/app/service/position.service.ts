import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ApiResponse } from './api-response';

const BASE_API = `${environment.baseApi}/position`

@Injectable({
  providedIn: 'root'
})
export class PositionService {

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
