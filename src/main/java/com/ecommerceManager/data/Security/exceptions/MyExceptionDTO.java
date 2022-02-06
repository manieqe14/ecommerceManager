package com.ecommerceManager.data.Security.exceptions;

import org.springframework.http.HttpStatus;

public class MyExceptionDTO {
	
	private HttpStatus statusCode;
	private String message;
	
	public MyExceptionDTO(MyException exception) {
		this.statusCode = exception.getStatusCode();
		this.message = exception.getMessage();
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	

}
