package com.example.rest.controller;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value = "/listAll")
    public List<CarInventory> getInventory() {
        return inventoryService.returnInventory();
    }

    @GetMapping(value = "/getById/{inventoryId}")
    public CarInventory getInventory(@RequestParam String inventoryId) {
        return inventoryService.getCarInventory(inventoryId);
    }

    @PostMapping(value = "/put/")
    public Car addToInventory(@RequestBody(required = true) Car car) {
        return inventoryService.addToInventory(car);
    }

    @DeleteMapping(value = "/del/{inventoryId}")
    public CarInventory deleteFromInventory(@PathParam(value = "inventoryId") String inventoryId) {
        return inventoryService.deleteFromInventory(inventoryId);
    }
}
