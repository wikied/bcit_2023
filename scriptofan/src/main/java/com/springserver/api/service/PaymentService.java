package com.springserver.api.service;

import com.springserver.api.model.Payment;
import com.springserver.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Iterable<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public String addNewPayment(String paymentType, String createBy) {
        if (paymentType == null || paymentType.isEmpty()) {
            throw new IllegalArgumentException("Payment type cannot be empty");
        }

        if (createBy == null || createBy.isEmpty()) {
            throw new IllegalArgumentException("Created by field cannot be empty");
        }

        Payment newPayment = new Payment();
        newPayment.setPaymentType(paymentType);
        newPayment.setCreatedBy(createBy);
        newPayment.setCreateTime(Instant.now());
        paymentRepository.save(newPayment);

        return "Saved";
    }

    public String removePayment(String id, String deletedBy) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (!optionalPayment.isPresent()) {
            throw new IllegalArgumentException("Payment with id " + id + " not found");
        }

        Payment payment = optionalPayment.get();
        payment.setDeletedBy(deletedBy);
        payment.setDeleteTime(Instant.now());
        paymentRepository.save(payment);

        return "Saved";
    }

}