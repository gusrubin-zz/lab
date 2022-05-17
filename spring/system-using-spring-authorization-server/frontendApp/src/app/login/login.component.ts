import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { OAuthService } from 'angular-oauth2-oidc/oauth-service';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public isLoggedIn = false;

  loginForm = this.formBuilder.group({
    username: '',
    password: ''
  });

  constructor(
    private oauthService: OAuthService,
    private _service: AuthService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    // this.isLoggedIn = this._service.checkCredentials();
    // console.log("window.location=", this.window.location);
    let i = window.location.href.indexOf('code');
    if (!this.isLoggedIn && i != -1) {
      this._service.retrieveToken(window.location.href.substring(i + 10));
    } else {

      // const parameters = new URLSearchParams(window.location.search);
      // console.log("parameters=", parameters);
      // console.log("parameters.get(\"code\")", parameters.get("code"));

      window.location.href = environment.oauth2IdpAuthorizeUri + '?'// 'http://auth-server:9001/oauth2/authorize?' 
        + 'response_type=code&'
        + 'scope=openid&'
        + 'client_id=' + environment.oauth2ClientId + '&'
        + 'redirect_uri=' + environment.oauth2LoginRedirectUri;
    }
  }

  onSubmit(): void {
    this.loginForm.reset();
    this._service.login();
  }

  public login() {
    this.oauthService.initLoginFlow();
  }

  public logoff() {
    this.oauthService.logOut();
  }

  public get name() {
    let claims = this.oauthService.getIdentityClaims();
    if (!claims) return null;
    return null;
  }

}
