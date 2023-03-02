package com.springserver.api.controller;

import com.springserver.api.model.Payment;
import com.springserver.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository; //to use category repository

    @GetMapping("/payment/all")
    public @ResponseBody Iterable<Payment> createNewPayments(){
        return paymentRepository.findAll();
    }
    @PostMapping("/payment/add")
    public @ResponseBody String addNewCategory (@RequestParam String paymentType, @RequestParam String createBy) {
        Payment newPayment = new Payment();
        newPayment.setPaymentType(paymentType);
        newPayment.setCreatedBy(createBy);
        newPayment.setCreateTime(Instant.now());
        paymentRepository.save(newPayment);
        return "Saved";
    }

    @PostMapping ("payment/find/{id}/remove")
    public @ResponseBody String removePayment(@PathVariable String id, @RequestParam String deletedBy) {
        Payment paymentID = paymentRepository.findById(id).get();
        paymentID.setDeletedBy(deletedBy);
        paymentID.setDeleteTime(Instant.now());
        paymentRepository.save(paymentID);
        return "Saved";
    }
}
