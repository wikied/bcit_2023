package com.springserver.api.controller;

import com.springserver.api.model.Garment;
import com.springserver.api.model.ShoppingCart;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.service.GarmentService;
import com.springserver.api.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private GarmentService garmentService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    public ShoppingCart create(@RequestParam String garment) {
        final ShoppingCart shoppingCart = shoppingCartService.create();
        shoppingCart.addGarment(garmentService.getGarment(garment));

        return shoppingCart;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> add(@PathVariable String id, @RequestParam String garment) {
        final ShoppingCart shoppingCart = shoppingCartService.get(UUID.fromString(id));
        if (shoppingCart == null) {
            throw new ResourceNotFoundException("ShoppingCart", "id", id);
        }

        shoppingCart.addGarment(garmentService.getGarment(garment));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ShoppingCart get(@PathVariable String id) {
        final ShoppingCart shoppingCart = shoppingCartService.get(UUID.fromString(id));
        if (shoppingCart == null) {
            throw new ResourceNotFoundException("ShoppingCart", "id", id);
        }

        return shoppingCart;
    }
}
