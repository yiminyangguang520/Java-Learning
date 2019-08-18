import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {PageNotFoundComponent} from './not-found.component';
import {AuthComponent} from './auth/auth.component';
import {VideosComponent} from './videos/videos.component';
import {ListComponent} from './videos/list/list.component';
import {AddComponent} from './videos/add/add.component';
import {WatchComponent} from './videos/watch/watch.component';
import {AuthGuard} from './_guards/auth.guard';

const routes: Routes = [
  {path: '', component: AuthComponent},
  {
    path: 'videos', component: VideosComponent, canActivate: [AuthGuard],
    children: [
      {path: '', component: ListComponent},
      {path: 'watch/:id', component: WatchComponent},
      {path: 'add', component: AddComponent},
    ]
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
