import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiResponse } from './api-response';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<ApiResponse>('http://localhost:8080/department', {params: form})
  }
}
