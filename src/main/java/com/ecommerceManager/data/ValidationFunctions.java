package com.ecommerceManager.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import com.ecommerceManager.data.Security.exceptions.MyException;
import com.ecommerceManager.data.models.Fakturownia;

public class ValidationFunctions {
	
	public static boolean fakturowniaValidation(Fakturownia fakturownia) {
		if(!postCodeValidation(fakturownia.getSeller_post_code())) 
			throw new MyException(HttpStatus.BAD_REQUEST, "Postcode validation error");
		return true;
		
	}
	
	private static boolean postCodeValidation(String postcode) {
		
		Pattern pattern = Pattern.compile("[0-9]{2}-[0-9]{3}$");
		Matcher matcher = pattern.matcher(postcode);
		return matcher.find();
		
	}
	

}
