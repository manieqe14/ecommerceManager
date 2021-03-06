package com.ecommerceManager.temp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.ecommerceManager.data.models.BillingRepo;
import com.ecommerceManager.data.models.Invoice;
import com.ecommerceManager.data.models.LineItem;
import com.ecommerceManager.data.models.LineItemRepo;
import com.ecommerceManager.data.models.MetaData;
import com.ecommerceManager.data.models.MetaDataRepo;
import com.ecommerceManager.data.models.Order;
import com.ecommerceManager.data.models.OrderRepo;
import com.ecommerceManager.data.models.ShippingRepo;
import com.ecommerceManager.data.models.Shop;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataGetter {
	
	private OrderRepo orderRepo;
	private MetaDataRepo metaDataRepo;
	private LineItemRepo lineItemRepo;
	private ShippingRepo shippingRepo;
	private BillingRepo billingRepo;
	
	private final String httpSuffix = "/wp-json/wc/v3/orders?per_page=50";
	private Shop shop;
	
	private HttpRequest request;
	private HttpResponse<String> response;
	
	public DataGetter(Shop shop, OrderRepo orderRepo, MetaDataRepo metaDataRepo, LineItemRepo lineItemRepo, BillingRepo billingRepo, ShippingRepo shippingRepo) {
		this.orderRepo = orderRepo;
		this.shop = shop;
		this.lineItemRepo = lineItemRepo;
		this.metaDataRepo = metaDataRepo;
		this.shippingRepo = shippingRepo;
		this.billingRepo = billingRepo;
	}
	
	public String refreshOrders() {
		String encoding = Base64Coder.encodeString(shop.getUsername() + ":" + shop.getPassword());
		try {
			request = HttpRequest.newBuilder().uri(new URI(shop.getSiteUrl() + httpSuffix)).header(HttpHeaders.AUTHORIZATION, "Basic " + encoding).build();
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			List<Order> orders = objectMapper.readValue(response.body(), new TypeReference<List<Order>>() {});
			if(orderRepo.findByWpId(shop.getShopId(), orders.get(0).getWp_id()).isEmpty()) {
				System.out.println("orders all right");
				return "OK!";
			}
			for(Order order : orders) {
				if(orderRepo.findByWpId(shop.getShopId(), order.getWp_id()).isEmpty()){
					  billingRepo.save(order.getBilling()); 
					  shippingRepo.save(order.getShipping());
					  for(MetaData metaData : order.getMeta_data()) { 
						  metaDataRepo.save(metaData); 
					  } 
					  for(LineItem lineItem : order.getLine_items())
					  { 
						  lineItemRepo.save(lineItem); 
					  }
					  
					order.setInvoice(new Invoice());
					order.setShop(shop); 
					orderRepo.save(order);
					
					for(MetaData metaData : order.getMeta_data()) { 
						metaData.setOrder(order);
						metaDataRepo.save(metaData); 
				  } 
				}
				
			}
			return "OK!";
			
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
