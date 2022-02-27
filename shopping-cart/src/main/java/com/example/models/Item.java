package com.example.models;

import lombok.Data;

@Data
public class Item {
    String name;
    Double price;

    public Item(String name, Double price){
        this.name = name;
        this.price = price;
    }
}
