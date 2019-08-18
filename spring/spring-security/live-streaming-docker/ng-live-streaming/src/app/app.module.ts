import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {VideosComponent} from './videos/videos.component';
import {ListComponent} from './videos/list/list.component';
import {AddComponent} from './videos/add/add.component';
import {WatchComponent} from './videos/watch/watch.component';
import {PageNotFoundComponent} from './not-found.component';

import {FeatherIconsPipe} from './_pipes/feather-icons.pipe';

import {AppRoutingModule} from './app-routing.module';
import {AuthGuard} from './_guards/auth.guard';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule, ToastrService} from 'ngx-toastr';
import {TokenInterceptor} from './_helpers/token.interceptor';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AuthService} from './_services/auth.service';
import {VideoService} from './_services/video.service';
import {NgMathPipesModule} from 'angular-pipes';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    VideosComponent,
    ListComponent,
    WatchComponent,
    AddComponent,
    PageNotFoundComponent,
    FeatherIconsPipe,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgMathPipesModule,
    ToastrModule.forRoot(),
    NgbModule.forRoot()
  ],
  providers: [
    AuthGuard,
    AuthService,
    VideoService,
    ToastrService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
