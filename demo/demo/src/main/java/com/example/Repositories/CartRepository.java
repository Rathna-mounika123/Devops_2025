// CartRepository.java
package com.example.Repositories;


import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.Cart;
 
 
public interface CartRepository extends JpaRepository<Cart, Integer> {
 
	

    // You can define custom query methods here if needed
 
	List<Cart> findByCustomerId(int customerId);
 
}

 