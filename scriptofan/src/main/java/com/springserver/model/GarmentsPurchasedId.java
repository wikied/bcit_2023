package com.springserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GarmentsPurchasedId implements Serializable {
    private static final long serialVersionUID = -6407830626720194694L;
    @Column(name = "transaction_id", nullable = false, length = 32)
    private String transactionId;

    @Column(name = "garment_id", nullable = false, length = 32)
    private String garmentId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getGarmentId() {
        return garmentId;
    }

    public void setGarmentId(String garmentId) {
        this.garmentId = garmentId;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || org.springframework.data.util.ProxyUtils.getUserClass(this) != org.springframework.data.util.ProxyUtils.getUserClass(o))
//            return false;
//        GarmentsPurchasedId entity = (GarmentsPurchasedId) o;
//        return Objects.equals(this.garmentId, entity.garmentId) &&
//                Objects.equals(this.transactionId, entity.transactionId);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(garmentId, transactionId);
    }

}