import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";

const API = `${environment.baseUrl}/ledger`

@Injectable({providedIn: 'root'})
export class LedgerEntryService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<any>(API, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${API}/${id}`)
  }

  create(form:any) {
    return this.http.post<any>(API, form)
  }

  update(id:number, form:any) {
    return this.http.put<any>(`${API}/${id}`, form)
  }
}
