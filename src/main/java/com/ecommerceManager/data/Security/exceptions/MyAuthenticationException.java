package com.ecommerceManager.data.Security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationException extends AuthenticationException{
	
	private String message;
	private HttpStatus status;

	public MyAuthenticationException(String msg, HttpStatus status) {
		super(msg);
		this.message = msg;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
