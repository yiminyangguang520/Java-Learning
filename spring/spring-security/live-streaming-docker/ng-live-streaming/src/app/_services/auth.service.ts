import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Observable} from 'rxjs';

@Injectable()
export class AuthService {
  private jwtHelper = new JwtHelperService();
  // store the URL so we can redirect after logging in
  private redirectUrl: string;
  // api URL
  private API_URL = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  // authenticate
  authenticate(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.API_URL + 'login', {
      username: username,
      password: password
    }, {observe: 'response'});
  }

  // register
  register(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.API_URL + 'api/v1.0/users/sign-up', {
      username: username,
      password: password,
    }, {observe: 'response'});
  }

  /**
   * EXTRA METHODS
   */

  // manually log in user
  logInUser(token: any) {
    localStorage.setItem('auth_token', token);
  }

  logout(): void {
    localStorage.removeItem('auth_token');
  }

  /**
   * JWT TOKEN & REDIRECT URL
   */
  getRedirectUrl(): string {
    return this.redirectUrl;
  }

  setRedirectUrl(url: string): void {
    this.redirectUrl = url;
  }

  getToken(): string {
    return localStorage.getItem('auth_token');
  }

  isAuthenticated(): boolean {
    if (!this.getToken()) {
      return false;
    }
    return !this.jwtHelper.isTokenExpired(this.getToken());
  }
}
