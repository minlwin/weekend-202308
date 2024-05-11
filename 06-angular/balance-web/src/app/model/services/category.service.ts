import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";

const BASE_URL = `${environment.baseUrl}/category`

@Injectable({providedIn: 'root'})
export class CategoryService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<any[]>(BASE_URL, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${BASE_URL}/${id}`)
  }

  create(form:any) {
    return this.http.post<any>(BASE_URL, form)
  }

  update(id:number, form:any) {
    return this.http.put<any>(`${BASE_URL}/${id}`, form)
  }
}
