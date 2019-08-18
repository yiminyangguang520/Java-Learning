import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class VideoService {
  // api URL
  private API_URL = 'http://localhost:8080/api/v1.0/';

  constructor(private http: HttpClient) {
  }

  // list videos
  list(): Observable<any> {
    return this.http.get<any>(this.API_URL + 'videos/list');
  }

  // get video
  get(id: String): Observable<any> {
    return this.http.get<any>(this.API_URL + 'videos/get/' + id);
  }

  // download video
  download(id: String): Observable<any> {
    return this.http.get(this.API_URL + 'videos/download/' + id, {responseType: 'blob'});
  }

  // upload video
  upload(title: string, file: File): Observable<any> {
    const fd = new FormData();
    fd.append('title', title);
    fd.append('file', file, file.name);
    return this.http.post<any>(this.API_URL + 'videos/upload', fd);
  }
}
