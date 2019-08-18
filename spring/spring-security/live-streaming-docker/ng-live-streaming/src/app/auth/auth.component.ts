import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../_services/auth.service';
import {User} from '../_models/user';
import {ToastrService} from 'ngx-toastr';
import * as _ from 'lodash';

@Component({
  templateUrl: './auth.component.html'
})
export class AuthComponent implements OnInit {
  user: User;
  loginMode = true;

  constructor(private router: Router,
              private authService: AuthService,
              private toastr: ToastrService) {
    this.user = new User();
  }

  ngOnInit() {
    // check for user logged in
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/videos']);
    }
  }

  login(): void {
    // validate form
    if (!this.user.username || !this.user.password) {
      return;
    }
    // send login request
    this.authService.authenticate(this.user.username, this.user.password)
      .subscribe(
        data => {
          // logged in user by saving authorization jwt token
          this.authService.logInUser(_.split(data.headers.get('Authorization'), ' ')[1]);
          // If no redirect has been set, use the default
          const redirect = this.authService.getRedirectUrl() ? this.authService.getRedirectUrl() : '/videos';
          this.router.navigateByUrl(redirect);
        },
        err => {
          this.toastr.error('Invalid login credentials. Please try again.', 'Error');
        });
  }

  signup(): void {
    // validate form
    if (!this.user.username || !this.user.password) {
      return;
    }
    // send login request
    this.authService.register(this.user.username, this.user.password)
      .subscribe(
        data => {
          // notify success
          this.toastr.success('Registration successful. Please login to access your account.', 'Success');
          this.loginMode = true;
          this.user = new User();
        },
        err => {
          this.toastr.error('Username already exists. Please choose another username.', 'Error');
        });
  }
}
