import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private apiUrl: string = "http://127.0.0.1:8080/api/auth";
  private registerUrl: string = this.apiUrl + "/register";
  private loginUrl: string = this.apiUrl + "/login"

  register(data: any): Observable<any> {
    return this.http.post<any>(this.registerUrl, data);
  }

  login(data: any): Observable<any> {
    return this.http.post<any>(this.loginUrl, data);
  }


}
