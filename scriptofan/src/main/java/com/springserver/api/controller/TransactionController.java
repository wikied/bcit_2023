package com.springserver.api.controller;

import com.springserver.api.model.Buyer;
import com.springserver.api.model.Payment;
import com.springserver.api.model.Transaction;
import com.springserver.api.model.User;
import com.springserver.api.repository.PaymentRepository;
import com.springserver.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public void createTransaction(Buyer buyer, Integer priceTotal, Integer quantity, String paymentType, String createdBy) {
        final Payment payment = new Payment();
        payment.setCreatedBy(createdBy);
        payment.setPaymentType(paymentType);
        paymentRepository.save(payment);

        final Transaction transaction = new Transaction();
        transaction.setTotal(priceTotal);
        transaction.setCreatedBy(createdBy);
        transaction.setPayment(payment);
        transaction.setQuantity(quantity);
        transaction.setBuyer(buyer);
        transaction.setCreatedBy(createdBy);
        transactionRepository.save(transaction);

    }

    public Iterable<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}
