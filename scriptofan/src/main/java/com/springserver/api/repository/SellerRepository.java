package com.springserver.api.repository;
import com.springserver.api.model.Seller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends CrudRepository<Seller, String>{
    public Optional<Seller> findById(String id);
}
