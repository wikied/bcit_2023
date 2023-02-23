package com.springserver.api.controller;

import com.springserver.api.model.Buyer;
import com.springserver.api.model.Transaction;
import com.springserver.api.model.User;
import com.springserver.api.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private BuyerRepository buyerRepository;

    @PostMapping("/create")
    public @ResponseBody String createTransaction(@RequestParam String buyerId,
                                                  @RequestParam Integer priceTotal,
                                                  @RequestParam Integer quantity,
                                                  @RequestParam String paymentType) {
        Optional<Buyer> buyerQuery = buyerRepository.findById(buyerId);
        if (buyerQuery.isPresent()) {
            final String createdBy = buyerQuery.get().getUser().getUserName();
            transactionController.createTransaction(buyerQuery.get(), priceTotal, quantity, paymentType, createdBy);

            return "Saved";
        }
        return "Invalid Data";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Transaction> getAllTransactions(Authentication authentication) {
        return transactionController.getAll();
    }
}
