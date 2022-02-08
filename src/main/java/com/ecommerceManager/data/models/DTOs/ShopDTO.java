package com.ecommerceManager.data.models.DTOs;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerceManager.data.models.Shop;

public class ShopDTO {
	
	private Shop shop;

	private long shopId;
	private String siteUrl;
	private String username;
	private String password;
	private String siteName;
	private List<Long> orders;
	private String userId;
	
	public ShopDTO(Shop shop) {
		shopId = shop.getShopId();
		siteUrl = shop.getSiteUrl();
		username = shop.getUsername();
		password = shop.getPassword();
		siteName = shop.getName();
		orders = shop.getOrders().stream().map(o -> o.getOrderId()).collect(Collectors.toList());
		userId = shop.getUser().getUsername();
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public List<Long> getOrders() {
		return orders;
	}

	public void setOrders(List<Long> orders) {
		this.orders = orders;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

}
