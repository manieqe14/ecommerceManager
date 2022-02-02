package com.ecommerceManager.data.Security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity handleException(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed");
		
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseException(ResponseStatusException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("No such user");
		
	}

}
