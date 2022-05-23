package com.gusrubin.springexampleresourceserver.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {// extends WebSecurityConfigurerAdapter {

	@Bean
	SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
		http
	// @formatter:off
//			.mvcMatcher("/api/examples/**")
			.authorizeExchange(exchanges -> exchanges
	                .pathMatchers("/actuator/**", "/api/public")
	                .permitAll()
	                .anyExchange()
	                .authenticated());
        // @formatter:on
		return http.build();
	}

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//	http
//	// @formatter:off
////		.authorizeRequests()
////		.anyRequest().authenticated();
//        	.mvcMatcher("/api/examples/**")
//        	.authorizeRequests()
//        	.mvcMatchers("/api/examples/**")
//        	.access("hasAuthority('SCOPE_examples.read')")
//        	.and()
//        	.oauth2ResourceServer()
//        	.jwt();
//	// @formatter:on
//    }

}
