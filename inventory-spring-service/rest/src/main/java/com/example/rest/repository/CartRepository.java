package com.example.rest.repository;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.model.UUIDGenerator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class CartRepository {

    Map<String, CarInventory> inventoryMap = new ConcurrentHashMap<>();

    public String getId() {
        return UUIDGenerator.getUuid();
    }

    public CarInventory findCar(String id) {
        CarInventory obj = inventoryMap.get(id);
        return obj;
    }

    public List<CarInventory> getCartItems() {
        return inventoryMap.values().stream().collect(Collectors.toList());
    }

    public Car addCar(CarInventory car) {
        String id = getId();
        CarInventory obj = inventoryMap.put(id, car);
        return car.getInventoryObject();
    }

    public CarInventory removeCar(CarInventory car) {

        return null;
    }

    public void checkout() {
        inventoryMap.clear();
    }

    public CarInventory removeCar(String id) {
        CarInventory obj = inventoryMap.remove(id);
        return obj;
    }

}
