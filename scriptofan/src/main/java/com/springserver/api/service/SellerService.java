package com.springserver.api.service;

import com.springserver.api.model.Seller;
import com.springserver.api.model.User;
import com.springserver.api.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;


@Component
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller createSeller(BigDecimal revenue, int sellerRating, User user, String createdBy){
        Seller s = new Seller();

        s.setRevenue(revenue);
        s.setSellerRating(sellerRating);
        s.setUser(user);
        s.setCreatedBy(createdBy);

        return sellerRepository.save(s);
    }

    public Seller update(Seller seller, Seller sellerRequest, String updatedBy){
        seller.setRevenue(sellerRequest.getRevenue());
        seller.setSellerRating(sellerRequest.getSellerRating());
        seller.setUpdatedBy(updatedBy);
        seller.setUpdateTime(Instant.now());
        return sellerRepository.save(seller);
    }

    public void delete(Seller seller, String deletedBy){
        seller.setDeletedBy(deletedBy);
        seller.setDeleteTime(Instant.now());

        sellerRepository.save(seller);
    }
}
