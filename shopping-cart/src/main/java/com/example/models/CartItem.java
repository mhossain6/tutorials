package com.example.models;

import lombok.Data;

@Data
public class CartItem {
    Item item;
    Integer quantity;
    Double finalPrice;

    public Double getPrice() {
        if (null == finalPrice)
            return item.getPrice() * quantity;
      return  finalPrice;
    }
}
