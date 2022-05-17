// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrl: 'http://gateway-service:4000/api',
  oauth2ClientId: 'angular-app-client',
  oauth2ClientSecret: 'angularsecret',
  // oauth2RedirectUri: 'http://ui-service:4200/oauth2/authorized',
  // oauth2IdpAuthorizeUri: 'http://auth-server:9001/oauth2/authorize',
  // oauth2AccessTokenUri: 'http://auth-server:9001/oauth2/token',
  oauth2RedirectUri: 'http://ui-service:4200/oauth2/authorized',
  oauth2LoginRedirectUri: 'http://ui-service:4200/login/oauth2/code',
  oauth2IdpAuthorizeUri: 'http://auth-service:9001/oauth2/authorize',
  oauth2AccessTokenUri: 'http://auth-service:9001/oauth2/token',

  publicInfoUri: 'http://127.0.0.1:8101/api/public',
  userRestrictInfoUri: 'http://127.0.0.1:8101/api/users'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
