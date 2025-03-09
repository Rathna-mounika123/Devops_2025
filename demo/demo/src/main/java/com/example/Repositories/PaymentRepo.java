package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entities.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    // You can add custom queries here if needed
}