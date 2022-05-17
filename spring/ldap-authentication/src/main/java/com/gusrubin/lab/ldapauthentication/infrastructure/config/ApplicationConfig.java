package com.gusrubin.lab.ldapauthentication.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import lombok.Getter;

@Getter
@Configuration
public class ApplicationConfig {

	@Value("${ldap.url}")
	private String ldapUrl;

	@Value("${ldap.user-base-dn}")
	private String ldapUserBaseDn;

	@Value("${ldap.user-attribute}")
	private String ldapUserAttribute;

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(ldapUrl);
		contextSource.setBase(ldapUserBaseDn);

		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}

}
