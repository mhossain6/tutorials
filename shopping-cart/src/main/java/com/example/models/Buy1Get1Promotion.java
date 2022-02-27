package com.example.models;

import lombok.Data;

import java.util.Map;

@Data
public class Buy1Get1Promotion extends  Promotion {
    Item buyItem;
    Item discountItem;
    Integer minBuyQty;
    Double discountRate;

    public Buy1Get1Promotion( Item buyItem,
    Item discountItem,
    Integer minBuyQty,
    Double discountRate) {
        this.buyItem = buyItem;
        this.discountItem = discountItem;
        this.minBuyQty = minBuyQty;
        this.discountRate = discountRate;
    }

    @Override
    public Cart applyPromotion(Cart cart) {
        Map<String, CartItem> busket = cart.getBusket();

        CartItem boughtItem = busket.get(buyItem.getName());
        if (null == boughtItem || boughtItem.getQuantity() < minBuyQty ){
            return cart;
        }

        Integer repeatDiscount = boughtItem.getQuantity() / minBuyQty;
        // Check discount items
        CartItem discItem = busket.get(discountItem.getName());
        if (null == discItem ){
            return cart;
        }
        // apply discount to all the items
        if (repeatDiscount >= discItem.getQuantity() ){
          Double price =   ( discItem.getQuantity() * discItem.getItem().getPrice() * discountRate ) / 100.0 ;
          discItem.setFinalPrice(price);
        }
        else {
            Double price =  ((  repeatDiscount * discItem.getItem().getPrice() * discountRate ) / 100.0 ) + ((discItem.getQuantity() - repeatDiscount) * discItem.getPrice());
            discItem.setFinalPrice(price);
        }

        return cart;

    }
}
