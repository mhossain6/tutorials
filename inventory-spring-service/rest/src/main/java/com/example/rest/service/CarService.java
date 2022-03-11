package com.example.rest.service;

import com.example.rest.model.Car;
import com.example.rest.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.getCars();
    }

    public Car addCar(Car car) {
        return carRepository.addCar(car);
    }

    public Car removeCar(String carId) {
        return carRepository.removeCar(carId);
    }
}
