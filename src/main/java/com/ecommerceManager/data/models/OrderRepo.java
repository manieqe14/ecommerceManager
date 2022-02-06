package com.ecommerceManager.data.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order, Long> {
	
	@Query("FROM Order WHERE shop_shop_id = ?1")
	List<Order> findByShop(long shopId);
	
	@Query("FROM Order WHERE shop_shop_id= ?1 AND status = ?2")
	List<Order> findByStatus(long shopId, String status);
	
	@Query("SELECT wp_id FROM Order o WHERE o.shop = ?1 AND o.wp_id = ?2")
	Optional<Long> findByWpId(long shopId, long wp_id);
}
