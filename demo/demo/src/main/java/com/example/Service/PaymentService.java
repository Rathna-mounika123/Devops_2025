package com.example.Service;
/*
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Payment;
import com.example.Repositories.PaymentRepo;

@Service
public class PaymentService {
	 @Autowired
	    private PaymentRepo paymentRepository;

	    // Create or update payment
	    public Payment savePayment(Payment payment) {
	        return paymentRepository.save(payment);
	    }

	    // Find a payment by its ID
	    public Optional<Payment> getPaymentById(int paymentId) {
	        return paymentRepository.findById(paymentId);
	    }

	    // Delete payment by ID
	    public void deletePaymentById(int paymentId) {
	        paymentRepository.deleteById(paymentId);
	    }
	}
*/






import java.util.List;

/*
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Payment;
import com.example.exception.PaymentNotFoundException;
import com.example.exception.PaymentProcessingException;
import com.example.Repositories.PaymentRepo;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepository;

    // Create or update payment
    public Payment savePayment(Payment payment) {
        try {
            return paymentRepository.save(payment);
        } catch (Exception e) {
            throw new PaymentProcessingException("Error processing payment", e);
        }
    }

    // Find a payment by its ID
    public Payment getPaymentById(int paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        
        // Check if payment exists, otherwise throw exception
        if (paymentOptional.isPresent()) {
            return paymentOptional.get();
        } else {
            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }

    // Delete payment by ID
    public void deletePaymentById(int paymentId) {
        try {
            Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
            if (paymentOptional.isPresent()) {
                paymentRepository.deleteById(paymentId);
            } else {
                throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
            }
        } catch (Exception e) {
            throw new PaymentProcessingException("Error deleting payment with ID " + paymentId, e);
        }
    }
}

*/



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Payment;
import com.example.Entities.CardDetails;
import com.example.exception.PaymentNotFoundException;
import com.example.exception.CardDetailsNotFoundException;
import com.example.exception.CardDetailsProcessingException;
import com.example.exception.PaymentProcessingException;
import com.example.Repositories.PaymentRepo;
import com.example.Repositories.CardDetailsRepo;  // Assuming there's a CardDetailsRepo

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepository;

    @Autowired
    private CardDetailsRepo cardDetailsRepository;  // Assuming this exists

    // Create or update payment
    public Payment savePayment(Payment payment) {
        try {
            // Save CardDetails first if applicable (you may need to check for null)
            if (payment.getCardDetails() != null) {
                try {
                    cardDetailsRepository.save(payment.getCardDetails());  // Assuming a separate CardDetails repository
                } catch (Exception e) {
                    throw new CardDetailsProcessingException("Error processing CardDetails", e);
                }
            }

            return paymentRepository.save(payment);
        } catch (Exception e) {
            throw new PaymentProcessingException("Error processing payment", e);
        }
    }

    // Find a payment by its ID
    public Payment getPaymentById(int paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            
            // Check if CardDetails is available for this payment
            if (payment.getCardDetails() == null) {
                throw new CardDetailsNotFoundException("Card details not found for payment ID " + paymentId);
            }
            
            return payment;
        } else {
            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }

    // Delete payment by ID
    public void deletePaymentById(int paymentId) {
        try {
            Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
            if (paymentOptional.isPresent()) {
                // First, delete CardDetails if necessary
                if (paymentOptional.get().getCardDetails() != null) {
                    cardDetailsRepository.delete(paymentOptional.get().getCardDetails());
                }
                
                paymentRepository.deleteById(paymentId);
            } else {
                throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
            }
        } catch (Exception e) {
            throw new PaymentProcessingException("Error deleting payment with ID " + paymentId, e);
        }
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();  // Fetches all payments from the database
    }
}

