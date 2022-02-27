package com.example.models;

import lombok.Data;

@Data
public class CartItem {
    Item item;
    Integer quantity;
    Double finalPrice;

    public Double getPrice() {
      return  finalPrice;
    }
}
