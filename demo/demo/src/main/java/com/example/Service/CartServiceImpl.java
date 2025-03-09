package com.example.Service;
/*
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Cart;
import com.example.Repositories.CartRepository;

@Service
public class CartServiceImpl implements ICartService {
 
    private final CartRepository cartRepository;
 
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
 
    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }
 
    @Override
    public Optional<Cart> getCartById(int itemId) {
        return cartRepository.findById(itemId);
    }
 
    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
 
    @Override
    public List<Cart> getCartsByCustomerId(int customerId) {
        return cartRepository.findByCustomerId(customerId);
    }
 
    @Override
    public void deleteCart(int itemId) {
        cartRepository.deleteById(itemId);
    }
}
*/



import com.example.Entities.Cart;
import com.example.Repositories.CartRepository;
import com.example.exception.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartById(int itemId) {
        return cartRepository.findById(itemId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> getCartsByCustomerId(int customerId) {
        return cartRepository.findByCustomerId(customerId);
    }
    
    
    @Override
    public Cart updateCart(int itemId, Cart updatedCart) {
        Optional<Cart> existingCartOpt = cartRepository.findById(itemId);
        if (existingCartOpt.isPresent()) {
            Cart existingCart = existingCartOpt.get();
            // Update fields with the new values from updatedCart
            existingCart.setName(updatedCart.getName());
            existingCart.setQuantity(updatedCart.getQuantity());
            existingCart.setPrice(updatedCart.getPrice());
            existingCart.setCustomerId(updatedCart.getCustomerId());
            
            // Save the updated cart back to the repository
            return cartRepository.save(existingCart);
        } else {
            // If the cart with the given itemId doesn't exist, you could throw an exception or return null
            return null; // You can modify this to throw a custom exception if needed
        }
    }

    @Override
    public void deleteCart(int itemId) throws CartNotFoundException {
        Optional<Cart> cart = cartRepository.findById(itemId);
        if (cart.isPresent()) {
            cartRepository.deleteById(itemId);
        } else {
            throw new CartNotFoundException("Cart not found with ID: " + itemId);
        }
    }
}
