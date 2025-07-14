package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.vo.User;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 8794484743983436451L;
	
	private final User user;
	
	public SecurityUser(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoleNames()
				.stream()
				.map(roleName -> new SimpleGrantedAuthority(roleName))
				.toList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public boolean isEnabled() {
		return "N".equals(user.getDeleted());
	}

	public User getUser() {
		return user;
	}
}
