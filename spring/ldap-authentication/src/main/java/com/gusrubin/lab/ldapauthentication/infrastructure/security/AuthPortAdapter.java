package com.gusrubin.lab.ldapauthentication.infrastructure.security;

import java.util.List;

import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.AuthenticatedLdapEntryContextCallback;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapEntryIdentification;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;
import com.gusrubin.lab.ldapauthentication.domain.AuthPort;
import com.gusrubin.lab.ldapauthentication.infrastructure.config.ApplicationConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthPortAdapter implements AuthPort {

	private final ApplicationConfig config;
	private final LdapTemplate ldapTemplate;
	private final ContextSource contextSource;

	@Autowired
	public AuthPortAdapter(ApplicationConfig applicationConfig, LdapTemplate ldapTemplate) {
		this.config = applicationConfig;
		this.ldapTemplate = ldapTemplate;
		this.contextSource = this.ldapTemplate.getContextSource();
	}

	@Override
	public boolean authenticate(String userDn, String credentials) {
		AuthenticatedLdapEntryContextCallback contextCallback = new AuthenticatedLdapEntryContextCallback() {
			public void executeWithContext(DirContext ctx, LdapEntryIdentification ldapEntryIdentification) {
				try {
					ctx.lookup(ldapEntryIdentification.getRelativeName());
				} catch (NamingException | javax.naming.NamingException e) {
					throw new IllegalStateException("Failed to lookup " + ldapEntryIdentification.getRelativeName(), e);
				}
			}
		};

		String uid = "(" + config.getLdapUserAttribute() + "=" + userDn + ")";

		return ldapTemplate.authenticate("", uid, credentials, contextCallback);
	}

	@Override
	public List<String> searchUser(String username) {
		if (this.contextSource == null) {
			throw new IllegalStateException("System not logged in LDAP server");
		}
		return ldapTemplate.search("OU=users,DC=enterpriseAD", "cn=" + username,
				(AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
	}

}
