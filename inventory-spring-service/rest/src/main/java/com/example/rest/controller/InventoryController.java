package com.example.rest.controller;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Secured({"ADMIN", "USER"})
    @GetMapping(value = "/list")
    public List<CarInventory> getInventory() {
        return inventoryService.returnInventory();
    }

    @Secured({"ADMIN"})
    @PostMapping(value = "/add")
    public Car addToInventory(@RequestBody Car car) {
        return inventoryService.addToInventory(car);
    }

    @Secured({"ADMIN"})
    @DeleteMapping(value = "/delete/{inventoryId}")
    public Car deleteFromInventory(@PathParam(value = "inventoryId") String inventoryId) {
        return inventoryService.deleteFromInventory(inventoryId);
    }
}
