import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {VideoService} from '../../_services/video.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {
  videos = [];
  loaded: boolean;

  constructor(private router: Router,
              private videoService: VideoService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    this.videoService.list()
      .subscribe(
        data => {
          this.videos = data;
          this.loaded = true;
        }, err => {
          this.loaded = true;
        });
  }
}
