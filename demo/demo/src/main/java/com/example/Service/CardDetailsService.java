package com.example.Service;
/*
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.CardDetails;
import com.example.Repositories.CardDetailsRepo;

@Service
public class CardDetailsService {


    @Autowired
    private CardDetailsRepo cardDetailsRepository;

    // Create or update CardDetails
    public CardDetails saveCardDetails(CardDetails cardDetails) {
        return cardDetailsRepository.save(cardDetails);
    }

    // Get CardDetails by its ID
    public Optional<CardDetails> getCardDetailsById(int cardId) {
        return cardDetailsRepository.findById(cardId);
    }

    // Delete CardDetails by ID
    public void deleteCardDetailsById(int cardId) {
        cardDetailsRepository.deleteById(cardId);
    }
}
*/





import com.example.Entities.CardDetails;
import com.example.exception.CardDetailsNotFoundException;
import com.example.exception.CardDetailsProcessingException;
import com.example.Repositories.CardDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardDetailsService {

    @Autowired
    private CardDetailsRepo cardDetailsRepo;

    // Create or update CardDetails
    public CardDetails saveCardDetails(CardDetails cardDetails) {
        try {
            return cardDetailsRepo.save(cardDetails);
        } catch (Exception e) {
            throw new CardDetailsProcessingException("Error processing card details: " + e.getMessage(), e);
        }
    }

    // Get CardDetails by its ID
    public CardDetails getCardDetailsById(int cardId) {
        Optional<CardDetails> cardDetailsOptional = cardDetailsRepo.findById(cardId);

        if (cardDetailsOptional.isPresent()) {
            return cardDetailsOptional.get();
        } else {
            throw new CardDetailsNotFoundException("Card details with ID " + cardId + " not found");
        }
    }

    // Delete CardDetails by ID
    public void deleteCardDetailsById(int cardId) {
        Optional<CardDetails> cardDetailsOptional = cardDetailsRepo.findById(cardId);
        if (cardDetailsOptional.isPresent()) {
            cardDetailsRepo.deleteById(cardId);
        } else {
            throw new CardDetailsNotFoundException("Card details with ID " + cardId + " not found");
        }
    }

	public List<CardDetails> getAllCardDetails() {
		return cardDetailsRepo.findAll();
	}
}
