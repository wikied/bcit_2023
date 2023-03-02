package com.springserver.api.controller;

import com.springserver.api.model.Buyer;
import com.springserver.api.model.PrintingLabel;
import com.springserver.api.model.Transaction;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.BuyerRepository;
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
    private TransactionService transactionController;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @PostMapping
    public @ResponseBody Transaction createTransaction(Authentication authentication, @RequestParam String buyerId,
                                                  @RequestParam Integer priceTotal,
                                                  @RequestParam Integer quantity,
                                                  @RequestParam String paymentType) {
        Optional<Buyer> buyerQuery = buyerRepository.findById(buyerId);
        if (buyerQuery.isPresent()) {
            return transactionController.createTransaction(buyerQuery.get(), priceTotal, quantity, paymentType, authentication.getName());
        }

        throw new ResourceNotFoundException("Buyer", "id", buyerId);
    }

    @PutMapping("/{id}")
    public Transaction updatePrintingLabel(Authentication authentication, @PathVariable String id, @RequestBody Transaction transactionRequest) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction", "id", id);
        }
        return transactionController.update(transaction.get(), transactionRequest, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> deleteTransaction(Authentication authentication, @PathVariable String id) {
        Optional<Transaction> transaction = transactionController.getTransaction(id);
        if (transaction.isPresent()) {
            transactionController.deleteTransaction(transaction.get(), authentication.getName());
            return ResponseEntity.ok().build();
        }
        
        throw new ResourceNotFoundException("Transaction", "id", id);
    }

    @GetMapping()
    public @ResponseBody Iterable<Transaction> getAllTransactions(Authentication authentication) {
        return transactionController.getAll();
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
