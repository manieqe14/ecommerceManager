package com.ecommerceManager.data.Security.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerceManager.data.UserPrincipal;
import com.ecommerceManager.data.models.Shop;

public class CheckUserPermission {
	
	public static boolean checkPermissionToShop(UserPrincipal user, String requestURL) {
		String shopId = requestURL.replaceAll("[.]*/shop/", "").replaceAll("\\/.*$", "").trim();
		if(shopId.matches("^[0-9]+$")) {
			List<Shop> shops = user.getShops();
			if(shops.stream().map(s -> s.getShopId()).filter(s -> Long.parseLong(shopId) == s).collect(Collectors.toList()).isEmpty())
			{
				System.out.println("false");
				return false;
			}
			else {
				System.out.println("true");
				return true;
			}
		}
		else 
			System.out.println("true2");
			return true;
		
	}

}
