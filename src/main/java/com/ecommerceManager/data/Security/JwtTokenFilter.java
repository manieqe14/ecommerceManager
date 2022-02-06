package com.ecommerceManager.data.Security;

import java.io.IOException;

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

import com.ecommerceManager.data.UserPrincipal;
import com.ecommerceManager.data.Security.exceptions.CheckUserPermission;
import com.ecommerceManager.data.Security.exceptions.MyAuthenticationException;
import com.ecommerceManager.data.Security.exceptions.RestAuthenticationEntryPoint;


@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	
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
			restAuthenticationEntryPoint.commence(request, response, new MyAuthenticationException("JWT Expired", HttpStatus.UNAUTHORIZED));
			return;
		}
		
		UserPrincipal user = (UserPrincipal) myUserDetailsService.loadUserByUsername(JwtTokenUtil.getUsernameFromToken(token));
		if(!CheckUserPermission.checkPermissionToShop(user, request.getRequestURI())) {
			restAuthenticationEntryPoint.commence(request,  response, new MyAuthenticationException("Shop forbidden", HttpStatus.UNAUTHORIZED));
			return;
		} 
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		
	}
	
	

}
