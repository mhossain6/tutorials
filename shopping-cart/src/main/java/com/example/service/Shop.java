package com.example.service;

import com.example.models.Cart;
import com.example.models.CartItem;
import com.example.models.Item;
import com.example.models.Promotion;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    Cart cart = new Cart();
    List<Promotion> promotionList = new ArrayList<>();

    public void addPromotion(Promotion promotion) {
        promotionList.add(promotion);
    }

    public void addItem(Item item) {
        CartItem cartItem = cart.getCartItem(item.getName());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(1);
            cart.addIntoBasket(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }

    public void removeItem(Item item) {
        CartItem cartItem = cart.getCartItem(item.getName());
        if (cartItem != null) {
            Integer newQty = cartItem.getQuantity() - 1;
            if (0 == newQty) {
                cart.getBusket().remove(item.getName());
            } else {
                cartItem.setQuantity(newQty);
            }
        }
    }

    public Double checkout() {
        promotionList.forEach(promotion -> {
            promotion.applyPromotion(cart);
        });
        final List<Double> finalPrice = new ArrayList<>();
        Double price = Double.valueOf(0);
        finalPrice.add(price);
        cart.getBusket().forEach((k, v) -> {
            finalPrice.set(0, finalPrice.get(0) + v.getFinalPrice());
        });

        return finalPrice.get(0);
    }
}
