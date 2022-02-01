package com.ecommerceManager.controllers.helpers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FakturowniaHelper {
	
	public static String getInvoice(int id, String api_token) {
		HttpRequest httpRequest;
		try {
			httpRequest = HttpRequest.newBuilder(
					new URI("https://mariusz-pacyga.fakturownia.pl/invoices/" + id + ".json?api_token=" + api_token)).build();
			HttpResponse response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
			return response.body().toString();
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "";
		
	}

}
