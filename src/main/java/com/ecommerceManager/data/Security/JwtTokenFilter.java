package com.ecommerceManager.data.Security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerceManager.data.UserPrincipal;


@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(header == null) {
			filterChain.doFilter(request, response);
			return;
		}
		if(!header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		final String token = header.replace("Bearer ", "").trim();
		if(!JwtTokenUtil.validateToken(token)) {
			filterChain.doFilter(request, response);
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "token expired");
		}
		
		UserPrincipal user = (UserPrincipal) myUserDetailsService.loadUserByUsername(JwtTokenUtil.getUsernameFromToken(token));
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		
	}
	
	

}
