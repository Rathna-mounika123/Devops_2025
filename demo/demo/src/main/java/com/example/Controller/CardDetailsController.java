package com.example.Controller;
/*
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.Entities.CardDetails;
import com.example.Service.CardDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/card-details")
@Validated
public class CardDetailsController {

    @Autowired
    private CardDetailsService cardDetailsService;

    // Create or Update CardDetails with validation
    @PostMapping("/save")
    public ResponseEntity<CardDetails> createOrUpdateCardDetails(@Valid @RequestBody CardDetails cardDetails) {
        CardDetails savedCardDetails = cardDetailsService.saveCardDetails(cardDetails);
        return new ResponseEntity<>(savedCardDetails, HttpStatus.CREATED);
    }

    // Get CardDetails by ID
    @GetMapping("/{cardId}")
    public ResponseEntity<CardDetails> getCardDetailsById(@PathVariable int cardId) {
        Optional<CardDetails> cardDetails = cardDetailsService.getCardDetailsById(cardId);
        if (cardDetails.isPresent()) {
            return new ResponseEntity<>(cardDetails.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete CardDetails by ID
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCardDetailsById(@PathVariable int cardId) {
        Optional<CardDetails> cardDetails = cardDetailsService.getCardDetailsById(cardId);
        if (cardDetails.isPresent()) {
            cardDetailsService.deleteCardDetailsById(cardId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
*/







import com.example.Dto.ErrorResponse;
import com.example.Entities.CardDetails;
import com.example.Service.CardDetailsService;
import com.example.exception.CardDetailsNotFoundException;
import com.example.exception.CardDetailsProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/card-details")
@Validated
public class CardDetailsController {

    @Autowired
    private CardDetailsService cardDetailsService;

    // Create or Update CardDetails
    @PostMapping("/save")
    public ResponseEntity<?> createOrUpdateCardDetails(@Valid @RequestBody CardDetails cardDetails) {
        try {
            CardDetails savedCardDetails = cardDetailsService.saveCardDetails(cardDetails);
            return new ResponseEntity<>(savedCardDetails, HttpStatus.CREATED);
        } catch (CardDetailsProcessingException e) {
            ErrorResponse errorResponse = new ErrorResponse("Error processing card details: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllCardDetails() {
        try {
            // Fetch all card details from the service
            List<CardDetails> allCardDetails = cardDetailsService.getAllCardDetails();
            return ResponseEntity.ok(allCardDetails);
        } catch (Exception e) {
            // Return a proper error message in case of an exception
            ErrorResponse errorResponse = new ErrorResponse("An error occurred while retrieving card details: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    

    // Get CardDetails by ID
    @GetMapping("/{cardId}")
    public ResponseEntity<?> getCardDetailsById(@PathVariable int cardId) {
        try {
            CardDetails cardDetails = cardDetailsService.getCardDetailsById(cardId);
            return ResponseEntity.ok(cardDetails);
        } catch (CardDetailsNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("Card details not found: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    // Delete CardDetails by ID
    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCardDetailsById(@PathVariable int cardId) {
        try {
            cardDetailsService.deleteCardDetailsById(cardId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CardDetailsNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("Card details not found: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (CardDetailsProcessingException e) {
            ErrorResponse errorResponse = new ErrorResponse("Error deleting card details: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Global Exception Handler for MethodArgumentNotValidException (if validation fails)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                                        .getAllErrors()
                                        .stream()
                                        .map(e -> e.getDefaultMessage())
                                        .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("Validation failed", errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Global Exception Handler for CardDetailsProcessingException
    @ExceptionHandler(CardDetailsProcessingException.class)
    public ResponseEntity<ErrorResponse> handleCardDetailsProcessingException(CardDetailsProcessingException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Error processing card details: " + ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Global Exception Handler for CardDetailsNotFoundException
    @ExceptionHandler(CardDetailsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCardDetailsNotFoundException(CardDetailsNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Card details not found: " + ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
