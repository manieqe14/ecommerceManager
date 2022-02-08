package com.ecommerceManager.data.Security.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException{
	
	private HttpStatus statusCode;
	private String message;
	private String timestamp;
	
	public MyException(HttpStatus statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
		timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")).toString();
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}	
	
	

}
