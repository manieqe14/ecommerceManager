package com.ecommerceManager.controllers.helpers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;

import com.ecommerceManager.data.models.Invoice;
import com.ecommerceManager.data.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FakturowniaHelper {
	
	public static String getInvoice(long id, String api_token) {
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
	
	public static String generateInvoice(Order order) {
		String api_token = order.getShop().getFakturownia().getToken();
		FakturowniaRequest fakturowniaRequest = new FakturowniaRequest(order);
		ObjectMapper mapper = new ObjectMapper();
		
		HttpRequest httpRequest;
		try {
			System.out.println(mapper.writeValueAsString(fakturowniaRequest));
			httpRequest = HttpRequest.newBuilder(new URI("https://mariusz-pacyga.fakturownia.pl/invoices.json?api_token=" + api_token))
				.header("Accept", "application/json")
				.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{\"invoice\":" + mapper.writeValueAsString(fakturowniaRequest) + "}")).build();
			
			HttpResponse response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
			return response.body().toString();
			
		} catch (IOException | URISyntaxException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

}
