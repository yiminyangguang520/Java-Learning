import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoService} from '../../_services/video.service';
import {ToastrService} from 'ngx-toastr';
import * as FileSaver from 'file-saver';

@Component({
  templateUrl: './watch.component.html'
})
export class WatchComponent implements OnInit {
  video: any;
  loaded: boolean;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private videoService: VideoService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.videoService.get(params.id)
        .subscribe(
          data => {
            this.video = data;
            this.loaded = true;
          }, err => {
            this.loaded = true;
          });
    });
  }

  downloadVideo(): void {
    this.videoService.download(this.video.id)
      .subscribe(
        data => {
          console.log(data);
          const file = new File([data], this.video.url);
          FileSaver.saveAs(file);
        }, err => {
          console.log(err);
        });
  }
}
