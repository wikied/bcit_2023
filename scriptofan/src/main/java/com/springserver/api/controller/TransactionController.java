package com.springserver.api.controller;

import com.springserver.api.model.Buyer;
import com.springserver.api.model.Payment;
import com.springserver.api.model.PrintingLabel;
import com.springserver.api.model.Transaction;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.BuyerRepository;
import com.springserver.api.repository.PaymentRepository;
import com.springserver.api.repository.TransactionRepository;
import com.springserver.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    public @ResponseBody Transaction createTransaction(Authentication authentication, @RequestParam String paymentId, @RequestParam String buyerId,
                                                  @RequestParam Integer priceTotal,
                                                  @RequestParam Integer quantity) {
        Optional<Payment> paymentQuery = paymentRepository.findById(paymentId);
        if (paymentQuery.isEmpty()) {
            throw new ResourceNotFoundException("Payment", "id", paymentId);
        }

        Optional<Buyer> buyerQuery = buyerRepository.findById(buyerId);
        if (buyerQuery.isPresent()) {
            return transactionService.createTransaction(buyerQuery.get(), paymentQuery.get(), priceTotal, quantity, authentication.getName());
        }

        throw new ResourceNotFoundException("Buyer", "id", buyerId);
    }

    @PutMapping("/{id}")
    public Transaction updatePrintingLabel(Authentication authentication, @PathVariable String id, @RequestBody Transaction transactionRequest) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction", "id", id);
        }
        return transactionService.update(transaction.get(), transactionRequest, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> deleteTransaction(Authentication authentication, @PathVariable String id) {
        Optional<Transaction> transaction = transactionService.getTransaction(id);
        if (transaction.isPresent()) {
            transactionService.deleteTransaction(transaction.get(), authentication.getName());
            return ResponseEntity.ok().build();
        }
        
        throw new ResourceNotFoundException("Transaction", "id", id);
    }

    @GetMapping()
    public @ResponseBody Iterable<Transaction> getAllTransactions(Authentication authentication) {
        return transactionService.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Transaction getTransaction(@PathVariable String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction", "id", id);
        }

        return transaction.get();
    }
}
