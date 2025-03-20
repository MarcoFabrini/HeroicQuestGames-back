package com.hqc.back.utils;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
	ROLE_ADMIN, ROLE_USER, ROLE_MODERATOR;

	@Override
	public String getAuthority() {
		return name();
	}

}// enum
