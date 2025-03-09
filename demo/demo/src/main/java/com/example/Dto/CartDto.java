package com.example.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CartDto {

	@NotBlank(message = "Name is required.")
    private String name;

    @Positive(message = "Quantity must be greater than 0.")
    private int quantity;

    @Positive(message = "Price must be greater than 0.")
    private double price;

    @NotNull(message = "Cart customer ID should not be empty")
    private Integer customerId;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}