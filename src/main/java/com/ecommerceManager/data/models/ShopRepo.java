package com.ecommerceManager.data.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepo extends JpaRepository<Shop, Long> {
	
	
}
