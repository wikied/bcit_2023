package com.springserver.api.controller;

import com.springserver.api.model.Seller;
import com.springserver.api.repository.SellerRepository;
import com.springserver.api.service.SellerService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SellerService sellerService;


    @PostMapping
    @PermitAll
    public @ResponseBody Seller addNewSeller(@RequestParam BigDecimal revenue, @RequestParam int sellerRating){

        return null;
    }

}
