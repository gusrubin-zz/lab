import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PublicInfoComponent } from './public-info/public-info.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { HttpClientModule } from '@angular/common/http';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BottomBarComponent } from './bottom-bar/bottom-bar.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { APP_BASE_HREF, PlatformLocation } from '@angular/common';
import { OAuthModule } from 'angular-oauth2-oidc/angular-oauth-oidc.module';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    HomeComponent,
    BottomBarComponent,
    PublicInfoComponent,
    UserInfoComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    HttpClientModule,
    OAuthModule.forRoot(),
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    AppComponent,
    CookieService,
    // { provide: APP_BASE_HREF, useValue: '/portal' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
