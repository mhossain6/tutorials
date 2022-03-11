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
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/list")
    public List<CarInventory> getInventory() {
        return inventoryService.returnInventory();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add")
    public Car addToInventory(@RequestBody Car car) {
        return inventoryService.addToInventory(car);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete/{inventoryId}")
    public Car deleteFromInventory(@PathParam(value = "inventoryId") String inventoryId) {
        return inventoryService.deleteFromInventory(inventoryId);
    }
}
