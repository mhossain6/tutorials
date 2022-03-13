package com.example.rest.repository;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.model.UUIDGenerator;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InventoryRepository {

    Map<String, CarInventory> inventoryMap = new HashMap<>();

    public String getId() {
        return UUIDGenerator.getUuid();
    }

    public List<CarInventory> getInventory() {
        Collection<CarInventory> carInventory = inventoryMap.values();
        return carInventory.stream().collect(Collectors.toList());
    }

    public CarInventory getInventory(String inventoryId) {
        return inventoryMap.get(inventoryId);
    }

    public Car findCar(String id) {
        CarInventory obj = inventoryMap.get(id);
        return obj.getInventoryObject();
    }

    public Car addCar(Car car) {
        String id = getId();
        CarInventory obj = inventoryMap.put(id, new CarInventory(id, car));
        return obj.getInventoryObject();
    }

    public CarInventory removeCar(String id) {
        CarInventory obj = inventoryMap.remove(id);
        return obj;
    }

}
