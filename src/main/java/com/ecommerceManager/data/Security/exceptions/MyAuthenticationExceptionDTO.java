package com.ecommerceManager.data.Security.exceptions;

import org.springframework.http.HttpStatus;

public class MyAuthenticationExceptionDTO {
	
	private String message;
	private HttpStatus statusCode;
	
	
	public MyAuthenticationExceptionDTO(MyAuthenticationException exception) {
		this.message = exception.getMessage();
		this.statusCode = exception.getStatus();
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public HttpStatus getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
	

}
