package com.springserver.api.repository;

import com.springserver.api.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {
    public Optional<Payment> findById(String id);
}

