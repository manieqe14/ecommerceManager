package com.ecommerceManager.data.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order, Long> {
	
	@Query("FROM Order WHERE shop_shop_id = ?1")
	List<Order> findByShop(long shopId);
	
	@Query("FROM Order WHERE shop_shop_id= ?1 AND status = ?2")
	List<Order> findByStatus(long shopId, String status);
}
