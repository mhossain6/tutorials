package com.example.rest.controller;

import com.example.rest.model.Car;
import com.example.rest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@CrossOrigin("*")
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class CarController {

    @Autowired
    CarService carService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/")
    public List<Car> getCars() {
        return carService.getCars();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{carId}")
    public List<Car> getCarsById(@PathVariable(required = true) String carId) {
        return new ArrayList<>();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/")
    public Car putCar(@RequestBody Car inCar) {
        return carService.addCar(inCar);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/")
    public Car deleteCar(@RequestParam(required = false) String carId) {
        return carService.removeCar(carId);
    }
}