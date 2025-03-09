package com.example.Entities;

/*
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class CardDetails {
    @Id
    private int card_Id;
    @Column(name="payment_Id")
    private int payment_Id;
    @Column(name="card_number")
    private String card_number;
    @Column(name="card_holderName")
    private String card_holderName;
    @Column(name="expiryDate")
    private LocalDate expiryDate;
    @Column(name="cvv")
    private String cvv;

    // Getters and Setters
    public int getCard_Id() {
        return card_Id;
    }

    public void setCard_Id(int card_Id) {
        this.card_Id = card_Id;
    }

    public int getPayment_Id() {
        return payment_Id;
    }

    public void setPayment_Id(int payment_Id) {
        this.payment_Id = payment_Id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_holderName() {
        return card_holderName;
    }

    public void setCard_holderName(String card_holderName) {
        this.card_holderName = card_holderName;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "CardDetails [card_Id=" + card_Id + ", payment_Id=" + payment_Id + ", card_number=" + card_number
                + ", card_holderName=" + card_holderName + ", expiryDate=" + expiryDate + ", cvv=" + cvv + "]";
    }
}
*/


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class CardDetails {

    @Id
    @JsonProperty("cardId") 
    private int card_Id;

    @Column(name="payment_Id")
    @JsonProperty("paymentId") 
    private int payment_Id;

    @Column(name="card_number")
    @JsonProperty("cardNumber")
    private String card_number;

    @Column(name="card_holderName")
    @JsonProperty("cardHolderName") 
    private String card_holderName;

    @Column(name="expiryDate")
    @JsonProperty("expiryDate") 
    private LocalDate expiryDate;

    @Column(name="cvv")
    @JsonProperty("cvv") 
    private String cvv;

    // Getters and setters
    public int getCard_Id() {
        return card_Id;
    }

    public void setCard_Id(int card_Id) {
        this.card_Id = card_Id;
    }

    public int getPayment_Id() {
        return payment_Id;
    }

    public void setPayment_Id(int payment_Id) {
        this.payment_Id = payment_Id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_holderName() {
        return card_holderName;
    }

    public void setCard_holderName(String card_holderName) {
        this.card_holderName = card_holderName;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "CardDetails [card_Id=" + card_Id + ", payment_Id=" + payment_Id + ", card_number=" + card_number
                + ", card_holderName=" + card_holderName + ", expiryDate=" + expiryDate + ", cvv=" + cvv + "]";
    }
}

