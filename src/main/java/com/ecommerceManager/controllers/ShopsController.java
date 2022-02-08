package com.ecommerceManager.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerceManager.data.User;
import com.ecommerceManager.data.UserRepo;
import com.ecommerceManager.data.ValidationFunctions;
import com.ecommerceManager.data.Security.exceptions.MyException;
import com.ecommerceManager.data.models.Fakturownia;
import com.ecommerceManager.data.models.FakturowniaRepo;
import com.ecommerceManager.data.models.Shop;
import com.ecommerceManager.data.models.ShopRepo;
import com.ecommerceManager.data.models.DTOs.FakturowniaDTO;
import com.ecommerceManager.data.models.DTOs.ShopDTO;

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
	public ShopDTO addShop( @RequestBody Shop shop) {
		User user = userRepo.findByUsername(request.getUserPrincipal().getName());
		shop.setUser(user);
		shopRepo.save(shop);		
		return new ShopDTO(shop);
	}
	
	@GetMapping("/shop/{id}/info")
	public ShopDTO getInfo(@PathVariable long id) {
		return new ShopDTO(shopRepo.findById(id).orElse(new Shop()));
	}
	
	@PutMapping("/shop/{shopId}")
	public ShopDTO updateShop(@PathVariable long shopId, @RequestParam Optional<String> name) {
		if(name.isPresent()) {
			Shop shop = shopRepo.findById(shopId).orElse(new Shop());
			shop.setName(name.get());
			shopRepo.save(shop);
			
		}
		return new ShopDTO(shopRepo.findById(shopId).orElse(new Shop()));
	}
	
	@PostMapping("/shop/{shopId}/fakturowniaSettings")
	public FakturowniaDTO fakturowniaSettings(@PathVariable long shopId, @RequestBody Fakturownia fakturownia) {
		ValidationFunctions.fakturowniaValidation(fakturownia);
		fakturownia.setShop(shopRepo.findById(shopId).orElse(new Shop()));
		fakturowniaRepo.save(fakturownia);
		return new FakturowniaDTO(fakturownia);
		
	}
	
	@PutMapping("/shop/{shopId}/fakturowniaSettings")
	public FakturowniaDTO fakturowniaUpdateSettings(@PathVariable long shopId, @RequestBody Fakturownia fakturownia) {
		ValidationFunctions.fakturowniaValidation(fakturownia);
		Shop shop = shopRepo.findById(shopId).orElse(null);
		fakturownia.setShop(shop);
		fakturowniaRepo.save(fakturownia);
		return new FakturowniaDTO(fakturownia);
		
	}
	
	@GetMapping("/shop/{shopId}/fakturowniaSettings")
	public FakturowniaDTO getFakturowniaSettings(@PathVariable long shopId) {
		return new FakturowniaDTO(fakturowniaRepo.findByShop(shopRepo.findById(shopId).orElse(new Shop())));
	}
	
	//dopisać delete shop

}
