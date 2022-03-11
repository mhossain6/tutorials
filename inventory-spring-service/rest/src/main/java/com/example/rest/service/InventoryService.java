package com.example.rest.service;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<CarInventory> returnInventory() {
        return inventoryRepository.getInventory();
    }

    public Car addToInventory(Car car) {
        return null;
    }

    public Car deleteFromInventory(String inventoryId) {
        return inventoryRepository.removeCar(inventoryId);
    }
}
