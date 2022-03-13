package com.example.rest.service;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    InventoryService inventoryService;

    public List<CarInventory> getCartItems() {
        return cartRepository.getCartItems();
    }

    public Car addToCart(CarInventory car) {
        return cartRepository.addCar(car);
    }

    public CarInventory deleteFromCart(CarInventory car) {
        return cartRepository.removeCar(car);
    }

    public List<CarInventory> removeCartItem(String inventoryId) {
        List<CarInventory> inventory = cartRepository.getCartItems().stream().filter(t -> t.getId().compareToIgnoreCase(inventoryId) == 0).collect(Collectors.toList());

        final List<CarInventory> processedInventory = new ArrayList<>();

        if (inventory != null && inventory.size() > 0) {
            inventory.forEach(inventory1 -> {
                CarInventory deletedItem = deleteFromCart(inventory1);
                if (null != deletedItem) {
                    processedInventory.add(deletedItem);
                    inventoryService.addToInventory(deletedItem.getInventoryObject());
                }
            });
        }
        return processedInventory;
    }

    public List<CarInventory> putItemIntoCart(String inventoryId) {
        List<CarInventory> inventory = inventoryService.returnInventory().stream().filter(t -> t.getId().compareToIgnoreCase(inventoryId) == 0).collect(Collectors.toList());

        final List<CarInventory> processedInventory = new ArrayList<>();

        if (inventory != null && inventory.size() > 0) {
            inventory.forEach(inventory1 -> {
                CarInventory deletedItem = inventoryService.deleteFromInventory(inventory1.getId());
                if (null != deletedItem) {
                    addToCart(deletedItem);
                    processedInventory.add(deletedItem);
                }
            });
        }
        return processedInventory;
    }

    public void checkOut() {
        cartRepository.checkout();
    }

}
