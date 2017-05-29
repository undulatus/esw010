package com.pointwest.workforce.planner.security;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pointwest.workforce.planner.ui.domain.TokenUser;

@Service
public class JWTDecoderServiceImpl implements JWTDecoderService {
	private static final Logger log = LoggerFactory.getLogger(JWTDecoderServiceImpl.class);
	
	@Override
	public Map<String, Claim> decode(String token) throws JWTDecodeException{
		log.debug("MCI >> decode");
		Map<String, Claim> claims = null;
		try {
		    DecodedJWT jwt = JWT.decode(token);
		    claims = jwt.getClaims();
		    
		    for (Claim name : claims.values()) {
		    	log.debug("Value " + name.asString());
	        }
		    for (String name : claims.keySet()) {
		    	log.debug("Key " + name);
	        }
		    
		} catch (JWTDecodeException jwte){
			log.error("An error of type " + jwte.getClass() + " occurred while trying to run decode(). "
					+ "Invalid on decoding token.");
			throw jwte;
		}
		log.debug("MCO << decode");
		return claims;
	}
	
	@Override
	public TokenUser getTokenUser(String token) {
		Map<String, Claim> claims = this.decode(token);
		String username = claims.get("rle").asString();
		String role = claims.get("usr").asString();
	    log.debug("this is the role : " + role);
	    log.debug("this is the username : " + username);
	    return new TokenUser(username, role);
	}
	    
	@Override
	public Boolean isTokenExpired(String token) {
        Map<String, Claim> claims = this.decode(token);
	    log.debug("this is the token expiration : " + claims.get("exp").asDate());
	    final Date expiration = claims.get("exp").asDate();
        return expiration.before(new Date());
    }
	
	@Override
	
	public Boolean isValidToken(String token) {
		if(getTokenUser(token) != null && !isTokenExpired(token)) {
			return true;
		}
		else {
			return false;
		}
	}
}
