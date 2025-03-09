package com.example.Dto;

import java.math.BigDecimal;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class PaymentDto {

   // @NotNull(message = "Payment method must not be null")
    //private String paymentMethod; 
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Payment method is required")
	private String paymentMethod;

    @NotNull(message = "Card details must not be null")
    private CardDetailsDto cardDetails;

    //@Positive(message = "COD amount must be positive")
    //private BigDecimal codAmount;
    
    @NotNull(message = "COD amount is required")
    @DecimalMin(value = "0.01", message = "COD amount must be greater than 0")
    private BigDecimal codAmount;

    @NotNull(message = "Shopping cart ID must not be null")
    private Integer shoppingCartId;

  //  @NotNull(message = "Payment status must not be null")
   // private String paymentStatus;
    
    @NotNull(message = "Payment status is required")
    @Enumerated(EnumType.STRING)
    private String paymentStatus;

    // Getters and Setters
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CardDetailsDto getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(CardDetailsDto cardDetails) {
        this.cardDetails = cardDetails;
    }

    public BigDecimal getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(BigDecimal codAmount) {
        this.codAmount = codAmount;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
