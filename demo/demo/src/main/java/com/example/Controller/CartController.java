package com.example.Controller;
/*
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.CartDto;
import com.example.Entities.Cart;
import com.example.Service.ICartService;
import com.example.exception.CartNotFoundException;

@RestController
@RequestMapping("/carts")
@Validated
public class CartController {

    private final ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@Validated @RequestBody CartDto cartDto) {
        try {
            // Convert CartDto to Cart entity
            Cart cart = new Cart();
            cart.setName(cartDto.getName());
            cart.setQuantity(cartDto.getQuantity());
            cart.setPrice(cartDto.getPrice());
            cart.setCustomerId(cartDto.getCustomerId());

            // Save the Cart
            Cart savedCart = cartService.addCart(cart);
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error adding cart: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a cart by ID
    @GetMapping("/{itemId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int itemId) {
        Optional<Cart> cart = cartService.getCartById(itemId);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            throw new CartNotFoundException("Cart not found with ID: " + itemId);
        }
    }

    // Get all carts
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Get carts by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable int customerId) {
        List<Cart> carts = cartService.getCartsByCustomerId(customerId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Delete a cart by ID
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int itemId) {
        cartService.deleteCart(itemId);
        return ResponseEntity.noContent().build();
    }
}
*/


import com.example.Dto.CartDto;
import com.example.Entities.Cart;
import com.example.Service.ICartService;
import com.example.exception.CartNotFoundException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
@Validated
public class CartController {

    private final ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    // Add a cart
    /*@PostMapping("/add")
    public ResponseEntity<Cart> addCart(@Validated @RequestBody CartDto cartDto) {
        try {
            // Convert CartDto to Cart entity
            Cart cart = new Cart();
            cart.setName(cartDto.getName());
            cart.setQuantity(cartDto.getQuantity());
            cart.setPrice(cartDto.getPrice());
            cart.setCustomerId(cartDto.getCustomerId());

            // Save the Cart
            Cart savedCart = cartService.addCart(cart);
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error adding cart: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    
    
   /* @PostMapping("/add")
    public ResponseEntity<?> addCart(@Validated @RequestBody CartDto cartDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Build an error response based on validation errors
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            
            bindingResult.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append(" ");
            });
            
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            // Convert CartDto to Cart entity
            Cart cart = new Cart();
            cart.setName(cartDto.getName());
            cart.setQuantity(cartDto.getQuantity());
            cart.setPrice(cartDto.getPrice());
            cart.setCustomerId(cartDto.getCustomerId());

            // Save the Cart
            Cart savedCart = cartService.addCart(cart);
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error adding cart: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error adding cart.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    
    
    @PostMapping("/add")
    public ResponseEntity<?> addCart(@Validated @RequestBody CartDto cartDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Build an error response based on validation errors
            Map<String, String> errorMessageMap = new HashMap<>();
            
            bindingResult.getAllErrors().forEach(error -> {
                // Format error messages as: "cartDto.fieldName": "Error message"
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errorMessageMap.put("cartDto." + fieldName, errorMessage);
            });
            
            return new ResponseEntity<>(errorMessageMap, HttpStatus.BAD_REQUEST);
        }

        try {
            // Convert CartDto to Cart entity
            Cart cart = new Cart();
            cart.setName(cartDto.getName());
            cart.setQuantity(cartDto.getQuantity());
            cart.setPrice(cartDto.getPrice());
            cart.setCustomerId(cartDto.getCustomerId());

            // Save the Cart
            Cart savedCart = cartService.addCart(cart);
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error adding cart: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error adding cart.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a cart by ID
    @GetMapping("/{itemId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int itemId) {
        Optional<Cart> cart = cartService.getCartById(itemId);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            throw new CartNotFoundException("Cart not found with ID: " + itemId);
        }
    }

    // Get all carts
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Get carts by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable int customerId) {
        List<Cart> carts = cartService.getCartsByCustomerId(customerId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Delete a cart by ID
    /*@DeleteMapping("/customer/{itemId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int customer_Id) {
        try {
            cartService.deleteCart(customer_Id);
            return ResponseEntity.noContent().build();
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }*/
    
    
    @PutMapping("/{itemId}")
    public ResponseEntity<Cart> updateCart(@PathVariable int itemId, @RequestBody Cart updatedCart) {
        Cart cart = cartService.updateCart(itemId, updatedCart);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);  // Or return a custom error response
        }
    }
    
    @DeleteMapping("/cart/{itemId}")
    public ResponseEntity<String> deleteCart(@PathVariable int itemId) {
        try {
            // Call the service to delete the cart
            cartService.deleteCart(itemId);

            // Return a success message if deletion is successful
            return ResponseEntity.ok("Cart with itemId " + itemId + " successfully deleted.");
        } catch (CartNotFoundException e) {
            // If the cart with the given itemId is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with itemId " + itemId + " not found.");
        } catch (Exception e) {
            // If some other error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting the cart.");
        }
    }
    
}