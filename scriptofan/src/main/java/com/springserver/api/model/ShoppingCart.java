package com.springserver.api.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ShoppingCart {

    public ShoppingCart(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

    private Integer totalPrice;

    private List<Garment> garments = new ArrayList<>();

    public List<Garment> getGarments() {
        return garments;
    }

    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        for (Garment garment : garments) {
            totalPrice += garment.getPrice();
        }

        return totalPrice;
    }

    public void addGarment(Garment garment) {
        garments.add(garment);

        totalPrice = getTotalPrice();
    }

    public void removeGarment(Garment garment) {
        garments.remove(garment);

        totalPrice = getTotalPrice();
    }

    public UUID getUUID() {
        return uuid;
    }
}
