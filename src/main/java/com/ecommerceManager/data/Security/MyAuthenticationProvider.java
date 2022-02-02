package com.ecommerceManager.data.Security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ecommerceManager.data.User;
import com.ecommerceManager.data.UserRepo;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserRepo userRepo;
	
	private @Autowired HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication) {
		final String username = (String) authentication.getPrincipal();
		if(username.equals("")) {
			throw new BadCredentialsException("Login empty");
		}
		Optional<User> user = Optional.of(userRepo.findByUsername(authentication.getName()));
		String shopIdString = request.getRequestURI().replaceAll("/shop/", "").replaceAll("\\/.*$", "");
		
		UsernamePasswordAuthenticationToken token = null;
		if(user.isPresent()) {
			if(shopIdString != "") {
				final int shopId = Integer.valueOf(shopIdString);
				List<Long> shops = user.get().getShops().stream().filter(s -> s.getShopId() == shopId).map(s -> s.getShopId()).collect(Collectors.toList());
				if(shops.isEmpty()) throw new BadCredentialsException("Not allowed");	
			}
				
			token = new UsernamePasswordAuthenticationToken(user.get().getUsername(), authentication.getCredentials(), Arrays.asList(new SimpleGrantedAuthority(user.get().getRole().getName())));
		}
		else throw new BadCredentialsException("no user");
		token.setDetails(authentication.getDetails());
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
