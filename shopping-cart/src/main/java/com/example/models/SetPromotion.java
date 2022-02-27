package com.example.models;

import java.util.*;

public class SetPromotion extends Promotion {

    Set<Item> buyItem = new HashSet<>();
    Item discountItem;
    Double discountRate;

    @Override
    public Cart applyPromotion(Cart cart) {

        final Map<String, CartItem> busket = cart.getBusket();

        final List<Boolean> applyPromotion = new ArrayList<>();
        applyPromotion.add(true);
        buyItem.forEach(item -> {
            CartItem boughtItem = busket.get(item.getName());
            if (null == boughtItem) {
                applyPromotion.set(0, false);
            }
        });

        if (applyPromotion.get(0) == false) return cart;

        // Check discount items
        CartItem discItem = busket.get(discountItem.getName());
        if (null == discItem) {
            return cart;
        }

        Double price = (discItem.getQuantity() * discItem.getItem().getPrice() * discountRate) / 100.0;
        discItem.setFinalPrice(price);

        return cart;
    }
}
