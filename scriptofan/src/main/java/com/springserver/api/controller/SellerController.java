package com.springserver.api.controller;

import com.springserver.api.model.Seller;
import com.springserver.api.model.User;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.SellerRepository;
import com.springserver.api.repository.UserRepository;
import com.springserver.api.service.SellerService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerService sellerService;


    @PostMapping
    @PermitAll
    public @ResponseBody Seller addNewSeller(@RequestParam BigDecimal revenue, @RequestParam int sellerRating, @RequestParam String username, @RequestParam String createdBy){

        Optional<User> user = userRepository.findByUserName(username);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User", "name", username);
        }

        return sellerService.createSeller(revenue, sellerRating, user.get(), createdBy);
    }
    @PutMapping("/{id}")
    public Seller updateSeller(Authentication authentication, @PathVariable String id, @RequestBody Seller sellerRequest) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isEmpty()) {
            throw new ResourceNotFoundException("Seller", "id", id);
        }

        return sellerService.update(seller.get(), sellerRequest, authentication.getName());
    }
    @GetMapping("/{id}")
    public @ResponseBody Seller findSellerById(@PathVariable String id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isEmpty()) {
            throw new ResourceNotFoundException("Seller", "id", id);
        }
        return sellerRepository.findById(id).get();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeller(Authentication authentication, @PathVariable String id){
        Optional<Seller> seller = sellerRepository.findById(id);
        if(seller.isEmpty()){
            throw new ResourceNotFoundException("Seller", "id", id);
        }
        sellerService.delete(seller.get(), authentication.getName());
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public @ResponseBody Iterable<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }
}
