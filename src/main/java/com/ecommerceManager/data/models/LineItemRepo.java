package com.ecommerceManager.data.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LineItemRepo extends JpaRepository<LineItem, Long> {
	
	//zapytanie o produkty wg najwiekszej sprzedazy
	@Query("SELECT DISTINCT name, SUM(quantity) as total_sell FROM LineItem GROUP BY name ORDER BY total_sell DESC")
	List<Object> bestSellProducts(Long shopId);
	

}
