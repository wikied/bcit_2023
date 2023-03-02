package com.springserver.api.service;

import com.springserver.api.model.Buyer;
import com.springserver.api.model.Payment;
import com.springserver.api.model.Transaction;
import com.springserver.api.repository.PaymentRepository;
import com.springserver.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Transaction createTransaction(Buyer buyer, Integer priceTotal, Integer quantity, String paymentType, String createdBy) {
        Payment payment = new Payment();
        payment.setCreatedBy(createdBy);
        payment.setPaymentType(paymentType);
        payment = paymentRepository.save(payment);

        final Transaction transaction = new Transaction();
        transaction.setTotal(priceTotal);
        transaction.setCreatedBy(createdBy);
        transaction.setPayment(payment);
        transaction.setQuantity(quantity);
        transaction.setBuyer(buyer);
        transaction.setCreatedBy(createdBy);
        return transactionRepository.save(transaction);

    }

    public Iterable<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransaction(String transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public Transaction update(Transaction transaction, Transaction requestData, String updatedBy) {
        transaction.setQuantity(requestData.getQuantity());
        transaction.setTotal(requestData.getTotal());
        transaction.setUpdatedBy(updatedBy);
        transaction.setUpdateTime(Instant.now());
        transaction.setDate(Instant.now());

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Transaction transaction, String deletedBy) {
        transaction.setDeletedBy(deletedBy);
        transaction.setDeleteTime(Instant.now());
        transactionRepository.save(transaction);
    }
}
