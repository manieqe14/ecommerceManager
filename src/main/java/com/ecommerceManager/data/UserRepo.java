package com.ecommerceManager.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
	
	@Query("FROM User u LEFT JOIN FETCH u.shops WHERE u.username = ?1")
	User findByUsername(String username);

}
