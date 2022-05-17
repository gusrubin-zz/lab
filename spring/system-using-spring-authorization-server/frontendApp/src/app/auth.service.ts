import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { toBase64String } from '@angular/compiler/src/output/source_map';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { of } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from 'src/environments/environment';
import { Token } from './token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public clientId = environment.oauth2ClientId;
  public clientSecret = environment.oauth2ClientSecret;
  public redirectUri = environment.oauth2RedirectUri;

  constructor(
    private _http: HttpClient,
    private cookie: CookieService
  ) { }

  retrieveToken(code: string) {
    // let params = new URLSearchParams();
    // params.append('grant_type', 'authorization_code');
    // params.append('client_id', this.clientId);
    // params.append('client_secret', this.clientSecret);
    // params.append('redirect_uri', this.redirectUri);
    // params.append('code', code);

    const clientCredentialsString = this.clientId + ':' + this.clientSecret;

    let headers = new HttpHeaders()
      .set('Content-type', 'application/x-www-form-urlencoded; charset=utf-8')
      .set('Authorization', 'Basic ' + btoa(clientCredentialsString));

    let urlParams = new HttpParams()
      .set('grant_type', 'authorization_code')
      .set('client_id', this.clientId)
      // .set('client_secret', this.clientSecret)
      .set('redirect_uri', this.redirectUri)
      .set('code', code);

    this._http.post(environment.oauth2AccessTokenUri, null, { headers: headers, params: urlParams })
      .subscribe(
        data => console.log("access_token=", data)
        // data => this.saveToken(new Token(JSON.parse(data.toString()), 1)),
        // err => { 
        //   console.log("post token error=", err);
        //   alert('Invalid Credentials'); 
        );
  }

  saveToken(token: Token) {
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    this.cookie.set("access_token", token.access_token, expireDate);
    console.log('Obtained Access token');
    window.location.href = 'http://ui-service:4200/portal';
  }

  getResource(resourceUrl: string): Observable<any> {
    var headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + this.cookie.get('access_token')
    });
    return this._http.get(resourceUrl, { headers: headers })
    // .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  checkCredentials() {
    return this.cookie.check('access_token');
  }

  login() {
    window.location.href = environment.oauth2IdpAuthorizeUri + '?' +
      'response_type=code&scope=openid%20write%20read&client_id=' + this.clientId +
      '&redirect_uri=' + this.redirectUri;
    // 'http://localhost:8083/auth/realms/baeldung/protocol/openid-connect/auth?' +
    // 'response_type=code&scope=openid%20write%20read&client_id=' +
    // this._service.clientId + '&redirect_uri=' + this._service.redirectUri;
  }

  logout() {
    this.cookie.delete('access_token');
    window.location.reload();
  }

}
