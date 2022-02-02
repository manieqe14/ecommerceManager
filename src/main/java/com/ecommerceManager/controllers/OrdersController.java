package com.ecommerceManager.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceManager.controllers.helpers.FakturowniaHelper;
import com.ecommerceManager.data.models.BillingRepo;
import com.ecommerceManager.data.models.FakturowniaRepo;
import com.ecommerceManager.data.models.LineItemRepo;
import com.ecommerceManager.data.models.MetaDataRepo;
import com.ecommerceManager.data.models.Order;
import com.ecommerceManager.data.models.OrderRepo;
import com.ecommerceManager.data.models.ShippingRepo;
import com.ecommerceManager.data.models.Shop;
import com.ecommerceManager.data.models.ShopRepo;
import com.ecommerceManager.temp.DataGetter;

@RestController
public class OrdersController {
	
	@Autowired
	private ShopRepo shopRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private FakturowniaRepo fakturowniaRepo;
	
	@Autowired
	private MetaDataRepo metaDataRepo;
	@Autowired
	private LineItemRepo lineItemRepo;
	@Autowired
	private ShippingRepo shippingRepo;
	@Autowired
	private BillingRepo billingRepo;
	
	@RequestMapping("/shop/{id}/refreshOrders")
	public String refreshOrders(@PathVariable long id) {
		Shop shop = shopRepo.findById(id).orElse(new Shop());
		DataGetter getter = new DataGetter(shop, orderRepo, metaDataRepo, lineItemRepo, billingRepo, shippingRepo);
		List<Order> orders = getter.refreshOrders();
		StringBuffer result = new StringBuffer();
		for(Order order : orders) {
			result.append(order.toString());
		}
		return "Order refreshed!";
	}
	
	@RequestMapping("/shop/{id}/orders")
	public List<Order> getOrders(@PathVariable long id, @RequestParam(required = false) Optional<Integer> count, Optional<String> status){
		List<Order> orders = orderRepo.findByStatus(id, status.get());
		if (count.orElse(0) > orders.size()) count.equals(orders.size());
		return orders.subList(0, count.orElse(orders.size()-1));
	}
	
	@RequestMapping("/shop/{id}/orders/{orderId}/getMeta")
	public String getOrderMeta(@PathVariable long orderId) {
		Order order = orderRepo.findById(orderId).orElse(new Order());
		return order.getMeta_data().toString();
		
	}
	
	@RequestMapping("/shop/{id}/orders/{orderId}/getShipping")
	public String getShipping(@PathVariable long orderId) {
		Order order = orderRepo.findById(orderId).orElse(new Order());
		return order.getShipping().toString();
		
	}
	
	@RequestMapping("/shop/{id}/orders/{orderId}/getTimeCreated")
	public String getTimeCreated(@PathVariable long id, @PathVariable long orderId) {
		
		Order order = orderRepo.getById(orderId);
		LocalDateTime zdt = LocalDateTime.parse(order.getDate_created(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		
	}
	
	@GetMapping("/shop/{shopId}/order/{orderId}/invoice")
	public String getInvoice(@PathVariable Map<String, String> pathVariables) {
		Order order = orderRepo.findById(Long.parseLong(pathVariables.get("orderId"))).orElse(new Order());
		//return FakturowniaHelper.getInvoice(Long.parseLong(pathVariables.get("orderId")), fakturowniaRepo.findApiToken(pathVariables.get("shopId")));
		return FakturowniaHelper.generateInvoice(order);
	}
	
	@GetMapping("/shop/{shopId}/products/best")
	public List<Object> bestProducts(@PathVariable long shopId) {
		List<Object> objects = lineItemRepo.bestSellProducts(shopId);
		return objects;
	}
	

}
