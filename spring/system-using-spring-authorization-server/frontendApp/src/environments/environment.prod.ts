export const environment = {
  production: true,
  apiUrl: 'http://gateway-service:4000/api',
  oauth2ClientId: 'angular-app-client',
  oauth2ClientSecret: 'angularsecret',
  // oauth2RedirectUri: 'http://127.0.0.1:9000/login/oauth2/code/gateway-client-oidc',
  // oauth2IdpAuthorizeUri: 'http://localhost:9000/login',
  oauth2RedirectUri: 'http://api-gateway:4000/porta/oauth2/authorized',
  oauth2LoginRedirectUri: 'http://api-gateway:4000/porta/login/oauth2/code',
  oauth2IdpAuthorizeUri: 'http://api-gateway:4000/api/auth/oauth2/authorize',
  oauth2AccessTokenUri: 'http://api-gateway:4000/api/auth/oauth2/token',

  publicInfoUri: 'http://127.0.0.1:8101/api/public',
  userRestrictInfoUri: 'http://127.0.0.1:8101/api/users'
};
