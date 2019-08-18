import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Router} from '@angular/router';
import {catchError} from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {


  constructor(private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('auth_token');
    if (token) {
      request = request.clone({
        setHeaders: {
          'Authorization': 'Bearer ' + token
        }
      });
    }

    // check for JWT token expiration
    return next.handle(request).pipe(catchError(err => {
      // route the user to login if token expired
      if (err instanceof HttpErrorResponse && err.status === 401) {
        // remove token first
        localStorage.removeItem('auth_token');

        this.router.navigate(['/']);
      }
      const error = err.error.message || err.statusText;
      return throwError(error);
    }));
  }
}

