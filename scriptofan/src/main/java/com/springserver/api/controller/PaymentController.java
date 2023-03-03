package com.springserver.api.controller;

import com.springserver.api.model.Payment;
import com.springserver.api.repository.PaymentRepository;
import com.springserver.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository; //to use category repository
    private PaymentService paymentService;

    @GetMapping("/payment/all")
    public @ResponseBody Iterable<Payment> createNewPayments(){
        return paymentService.findAllPayments();
    }
    @PostMapping("/payment/add")
    public @ResponseBody String addNewCategory (@RequestParam String paymentType, @RequestParam String createBy) {
        return paymentService.addNewPayment(paymentType, createBy);
    }

    @PostMapping ("payment/find/{id}/remove")
    public @ResponseBody String removePayment(@PathVariable String id, @RequestParam String deletedBy) {
        return paymentService.removePayment(id, deletedBy);
    }
}
