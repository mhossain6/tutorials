package com.example.rest;


import com.example.models.Buy1Get1Promotion;
import com.example.models.Item;
import com.example.models.Promotion;
import com.example.service.Shop;

import java.util.HashMap;
import java.util.Map;

public class TestRun {

    static Map<String, Item> shelfItems = new HashMap<>();

    public static void main(String[] args) {

        Shop testShop = new Shop();

        Item shirt = new Item("Shirt", 100.0);
        Item pants = new Item( "Pant", 200.0);
        Item sunglass = new Item("Sunglass", 120.0 );
        Item shoe = new Item ("Shoe", 125.0);

        shelfItems.put(shirt.getName(), shirt);
        shelfItems.put(pants.getName(), pants);
        shelfItems.put(sunglass.getName(), sunglass);
        shelfItems.put(shoe.getName(), shoe);

        Promotion promotion1 = new Buy1Get1Promotion(shirt, pants, 1, 50.0);
        testShop.addPromotion(promotion1);
        Promotion promotion2 = new Buy1Get1Promotion(sunglass, shoe, 3, 100.0);
        testShop.addPromotion(promotion2);

        testShop.addItemToCart(shirt);
        testShop.addItemToCart(sunglass);
        testShop.addItemToCart(pants);
        testShop.addItemToCart(shoe);

        System.out.println(testShop.checkout());

    }
}
