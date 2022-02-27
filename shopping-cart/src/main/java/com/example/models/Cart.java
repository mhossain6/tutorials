package com.example.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Cart {
    Map<String, CartItem> busket = new HashMap<>();

    public CartItem getCartItem(String itemname) {
        return busket.get(itemname);
    }

    public void addIntoBasket(CartItem cartItem) {
        busket.put(cartItem.getItem().getName(), cartItem);
    }

    public Double checkOut() {
        final List<Double> finalPrice = new ArrayList<>();
        Double price = Double.valueOf(0);
        finalPrice.add(price);
        busket.forEach((k, v) -> {
            applyPromotio(k, v, finalPrice);
        });

        return finalPrice.get(0);
    }

    private void applyPromotio(String itemname, CartItem cartItem, final List<Double> finalPrice) {

    }

}

