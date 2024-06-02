import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  register(email: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, { email }, { responseType: 'text' });
  }

  verifyTempPassword(email: string, tempPassword: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/verify-temp-password`, { email, tempPassword }, { responseType: 'text' });
  }

  setNewPassword(email: string, newPassword: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/set-new-password`, { email, newPassword }, { responseType: 'text' });
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { email, password }, { responseType: 'text' })
  }

}
