package com.example.Service;
 
import java.util.List;

import java.util.Optional;

import com.example.Entities.Cart;
 
 
public interface ICartService {

	Cart addCart(Cart cart);

    Optional<Cart> getCartById(int itemId);

    List<Cart> getAllCarts();

    List<Cart> getCartsByCustomerId(int customerId);
    Cart updateCart(int itemId,Cart updateCart);
    //void deleteCart(String name);

	void deleteCart(int customer_Id);
 
}