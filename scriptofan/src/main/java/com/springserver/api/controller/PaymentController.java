package com.springserver.api.controller;

import com.springserver.api.model.Payment;
import com.springserver.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public @ResponseBody Iterable<Payment> createNewPayments(){
        return paymentService.findAllPayments();
    }
    @PostMapping
    public @ResponseBody String addNewCategory (Authentication authentication, @RequestParam String paymentType) {
        return paymentService.addNewPayment(paymentType, authentication.getName());
    }

    @DeleteMapping ("/{id}")
    public @ResponseBody String removePayment(Authentication authentication, @PathVariable String id) {
        return paymentService.removePayment(id, authentication.getName());
    }
}
