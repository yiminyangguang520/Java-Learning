import {Injectable} from '@angular/core';
import {CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../_services/auth.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const url: string = state.url;

    if (this.authService.isAuthenticated()) {
      return true;
    }

    // Store the attempted URL for redirecting
    this.authService.setRedirectUrl(url);

    // Navigate to the login page with extras
    this.router.navigate(['/']);

    return false;
  }
}
