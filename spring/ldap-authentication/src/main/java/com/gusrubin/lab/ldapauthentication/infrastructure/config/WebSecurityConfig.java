package com.gusrubin.lab.ldapauthentication.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final ApplicationConfig config;

	@Autowired
	public WebSecurityConfig(ApplicationConfig applicationConfig) {
		this.config = applicationConfig;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/auth/spring-security").fullyAuthenticated()
			.and().formLogin().defaultSuccessUrl("http://localhost:3001/auth/login")
//			.loginProcessingUrl("http://localhost:3001/auth/login")
			.and().authorizeRequests().anyRequest().permitAll();
		// @formatter:on		
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth.ldapAuthentication()
			.userDnPatterns("uid={0},ou=people,dc=springframework,dc=org")
			.contextSource()
			.url(config.getLdapUrl())
			.and()
			.passwordCompare()
//			.passwordEncoder(new BCryptPasswordEncoder())
			.passwordAttribute("userPassword");
		// @formatter:on
	}

}
