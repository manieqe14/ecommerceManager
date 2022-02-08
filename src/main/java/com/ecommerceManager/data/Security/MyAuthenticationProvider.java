package com.ecommerceManager.data.Security;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ecommerceManager.data.UserPrincipal;
import com.ecommerceManager.data.Security.exceptions.MyAuthenticationException;
import com.ecommerceManager.data.Security.exceptions.RestAuthenticationEntryPoint;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
		
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	private @Autowired HttpServletRequest request;
	

	@Override
	public Authentication authenticate(Authentication authentication) {
		final String username = (String) authentication.getPrincipal();
		if(username.equals("")) {
			throw new MyAuthenticationException("Login empty", HttpStatus.UNAUTHORIZED);
		}
		
		UserPrincipal user = (UserPrincipal) myUserDetailsService.loadUserByUsername(username);
		String shopIdString = request.getRequestURI().replaceAll("/shop/", "").replaceAll("\\/.*$", "");
		
		UsernamePasswordAuthenticationToken token = null;
		if(shopIdString != "") {
			final int shopId = Integer.valueOf(shopIdString);
			List<Long> shops = user.getShops().stream().filter(s -> s.getShopId() == shopId).map(s -> s.getShopId()).collect(Collectors.toList());
			if(shops.isEmpty()) {
				throw new MyAuthenticationException("Not allowed", HttpStatus.UNAUTHORIZED);
			}
		}
			
		token = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
		token.setDetails(authentication.getDetails());
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
