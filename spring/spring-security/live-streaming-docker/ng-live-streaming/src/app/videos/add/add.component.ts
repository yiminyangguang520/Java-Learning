import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {VideoService} from '../../_services/video.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  templateUrl: './add.component.html'
})
export class AddComponent implements OnInit {

  constructor(private router: Router,
              private videoService: VideoService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
  }

  upload(title: string, files: FileList): void {
    if (title.length === 0 || files.length === 0) {
      return;
    }
    // upload video
    this.videoService.upload(title, files[0])
      .subscribe(
        data => {
          this.toastr.success('Video uploaded.', 'Success');
          this.router.navigate(['/videos']);
        }, err => {
          this.toastr.error('Unable to upload video. Please, try again later.', 'Error');
        });
  }
}
