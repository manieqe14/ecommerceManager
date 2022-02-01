package com.ecommerceManager.data.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FakturowniaRepo extends JpaRepository<Fakturownia, Integer> {
	
	@Query("SELECT token FROM Fakturownia WHERE shop_id=?1")
	String findApiToken(String shopId);

}
