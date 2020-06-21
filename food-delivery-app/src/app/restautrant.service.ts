import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RestautrantService {

  constructor(private http: HttpClient) {}

  baseurl = 'http://localhost:8080';

  getAllCatalogue() {
    return this.http.get<any>('/api/restautrant/read');
  }
}
