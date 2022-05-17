package com.gusrubin.springauthorizationserver.domain.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gusrubin.springauthorizationserver.domain.role.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String username;
    private String password;
    @Transient
    private List<UserRole> roles;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	roles.stream().forEach(role -> {
	    GrantedAuthority grantedAuthority = new GrantedAuthority() {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthority() {
		    return role.getRoleName();
		}

	    };
	    grantedAuthorities.add(grantedAuthority);
	});
	return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
	return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
	return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
	return enabled;
    }

}
