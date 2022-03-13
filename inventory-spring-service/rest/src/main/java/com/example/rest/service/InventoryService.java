package com.example.rest.service;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<CarInventory> returnInventory() {
        return inventoryRepository.getInventory();
    }

    public List<CarInventory> getCar(Car car) {
        return inventoryRepository.getInventory().stream().filter(inventory -> inventory.getInventoryObject().getMake().compareToIgnoreCase(car.getMake()) == 0).collect(Collectors.toList());
    }

    public Car addToInventory(Car car) {
        return null;
    }

    public CarInventory getCarInventory(String inventoryId) {
        return inventoryRepository.getInventory(inventoryId);
    }

    public CarInventory deleteFromInventory(String inventoryId) {
        return inventoryRepository.removeCar(inventoryId);
    }
}
