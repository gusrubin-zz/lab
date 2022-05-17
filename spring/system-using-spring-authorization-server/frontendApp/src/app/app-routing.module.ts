import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PublicInfoComponent } from './public-info/public-info.component';
import { UserInfoComponent } from './user-info/user-info.component';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent, children: [
      {
        path: ':oauth2/code', component: LoginComponent
      }
    ]
  },
  { path: '', component: HomeComponent },//, pathMatch: 'full' },
  // { path: '**', redirectTo: 'home' },
  { path: 'public', component: PublicInfoComponent },
  { path: 'user-info', component: UserInfoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabledBlocking'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {

  get Routes() {
    return routes;
  }
}
