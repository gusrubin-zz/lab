package com.gusrubin.springauthclient.application;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ExampleController {

    private WebClient webClient;

    @GetMapping(value = "/examples")
    public String[] getArticles(
	    @RegisteredOAuth2AuthorizedClient("examples-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
	return this.webClient.get().uri("http://localhost:8101/api/examples")
		.attributes(getOauth2Attributes(authorizedClient)).retrieve().bodyToMono(String[].class).block();
    }
    
    private Consumer<Map<String, Object>> getOauth2Attributes(OAuth2AuthorizedClient authorizedClient) {
	Map<String, Object> map = new HashMap<>();
	map.put("accessToken", authorizedClient.getAccessToken());
	
	return (Consumer<Map<String, Object>>) map;
    }

}