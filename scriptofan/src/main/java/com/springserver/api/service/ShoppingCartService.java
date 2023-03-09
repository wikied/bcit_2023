package com.springserver.api.service;

import com.springserver.api.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class ShoppingCartService {
    private HashMap<UUID, ShoppingCart> shoppingCarts = new HashMap<>();

    public ShoppingCart create() {
        final ShoppingCart shoppingCart = new ShoppingCart(UUID.randomUUID());

        shoppingCarts.put(shoppingCart.getUUID(), shoppingCart);

        return shoppingCart;
    }

    public ShoppingCart get(UUID uuid) {
        return shoppingCarts.get(uuid);
    }
}
