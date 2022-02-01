package com.ecommerceManager.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceManager.data.User;
import com.ecommerceManager.data.UserRepo;
import com.ecommerceManager.data.models.Fakturownia;
import com.ecommerceManager.data.models.FakturowniaRepo;
import com.ecommerceManager.data.models.Shop;
import com.ecommerceManager.data.models.ShopRepo;

@RestController
public class ShopsController {
	
	@Autowired
	private ShopRepo shopRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private FakturowniaRepo fakturowniaRepo;
	
	private @Autowired HttpServletRequest request;
	
	@PostMapping("/shop/addShop")
	public String addShop( @RequestBody Shop shop) {
		User user = userRepo.findByUsername(request.getUserPrincipal().getName());
		shop.setUser(user);
		shopRepo.save(shop);		
		return "Shop added!";
	}
	
	@GetMapping("/shop/{id}/info")
	public Shop getInfo(@PathVariable long id) {
		return shopRepo.findById(id).orElse(new Shop());
	}
	
	@PostMapping("/shop/{shopId}/fakturowniaSettings")
	public Fakturownia fakturowniaSettings(@PathVariable long shopId, @RequestBody Fakturownia fakturownia) {
		fakturownia.setShop(shopRepo.findById(shopId).orElse(new Shop()));
		fakturowniaRepo.save(fakturownia);
		return fakturownia;
		
	}

}
