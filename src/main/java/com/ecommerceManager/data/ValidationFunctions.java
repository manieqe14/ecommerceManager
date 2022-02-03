package com.ecommerceManager.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationFunctions {
	
	public static boolean postCodeValidation(String postcode) {
		
		Pattern pattern = Pattern.compile("[0-9]{2}-[0-9]{3}$");
		Matcher matcher = pattern.matcher(postcode);
		return matcher.find();
		
	}
	

}
