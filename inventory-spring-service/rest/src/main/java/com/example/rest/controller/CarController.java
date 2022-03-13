package com.example.rest.controller;

import com.example.rest.model.Car;
import com.example.rest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@CrossOrigin("*")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping(value = "/listAll/")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping(value = "/getById/{carId}")
    public List<Car> getCarsById(@PathVariable(required = true) String carId) {
        return new ArrayList<>();
    }

    @PostMapping(value = "/put/")
    public Car putCar(@RequestBody(required = true) Car inCar) {
        return carService.addCar(inCar);
    }

    @DeleteMapping(value = "/del/")
    public Car deleteCar(@RequestBody(required = true) Car car) {
        return carService.removeCar(car);
    }
}