package com.example.rest.repository;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.model.UUIDGenerator;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class CartRepository {
    Map<String, CarInventory> inventoryMap = new HashMap<>();

    public String getId() {
        return UUIDGenerator.getUuid();
    }

    public CarInventory findCar(String id) {
        CarInventory obj = inventoryMap.get(id);
        return obj;
    }

    public Car addCar(CarInventory car) {
        String id = getId();
        CarInventory obj = inventoryMap.put(id, car);
        return obj.getInventoryObject();
    }

    public Car removeCar(String id) {
        CarInventory obj = inventoryMap.remove(id);
        return obj.getInventoryObject();
    }
}
