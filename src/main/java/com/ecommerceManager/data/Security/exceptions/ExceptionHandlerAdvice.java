package com.ecommerceManager.data.Security.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(MyException.class)
	public ResponseEntity<Object> handleResponseException(MyException e) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return ResponseEntity.status(e.getStatusCode())
				.header("Content-Type", "application/json")
				.body(mapper.writeValueAsString(new MyExceptionDTO(e)));
		
	}

}
