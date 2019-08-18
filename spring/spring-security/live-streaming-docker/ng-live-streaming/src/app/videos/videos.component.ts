import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../_services/auth.service';
import {ToastrService} from 'ngx-toastr';
import {User} from '../_models/user';

@Component({
  templateUrl: './videos.component.html'
})
export class VideosComponent implements OnInit {

  constructor(private router: Router,
              private authService: AuthService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
  }

  logout(): void {
     this.authService.logout();
    this.toastr.success('Logout successfully.', 'Success');
    this.router.navigate(['/']);
  }
}
