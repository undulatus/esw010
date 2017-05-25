package com.pointwest.workforce.planner.security;

import java.util.Map;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.pointwest.workforce.planner.ui.domain.TokenUser;

public interface JWTDecoderService {
	
	public Map<String, Claim> decode(String token) throws JWTDecodeException;

	Boolean isValidToken(String token);

	Boolean isTokenExpired(String token);

	TokenUser getTokenUser(String token);
}
