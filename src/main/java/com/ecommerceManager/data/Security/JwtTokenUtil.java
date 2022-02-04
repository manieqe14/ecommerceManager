package com.ecommerceManager.data.Security;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	private static final String secret = "secret";
	
	
	public static String generateToken(String username) {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, JWT_TOKEN_VALIDITY);
		return JWT.create().withSubject(username).withIssuedAt(date).withExpiresAt(cal.getTime()).sign(Algorithm.HMAC256(secret));
	}
	
	public static String getUsernameFromToken(String token) {
		return JWT.decode(token).getSubject();
	}
	
	public static boolean validateToken(String token) {
		Date date = new Date(System.currentTimeMillis());
		return date.before(JWT.decode(token).getExpiresAt());
	}
	
	

}
