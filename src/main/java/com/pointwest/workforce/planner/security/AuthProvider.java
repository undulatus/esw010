package com.pointwest.workforce.planner.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.pointwest.workforce.planner.ui.domain.TokenUser;

@Component
public class AuthProvider implements AuthenticationProvider {
	
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        TokenUser user = new TokenUser();
        user.setUsername("bryan user");
        user.setRole("bryan role");
        return (Authentication) user;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthProvider.class.isAssignableFrom(authentication);
    }
}
