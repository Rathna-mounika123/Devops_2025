package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entities.CardDetails;

@Repository
public interface CardDetailsRepo extends JpaRepository<CardDetails, Integer> {
    // You can add custom queries here if needed
}
