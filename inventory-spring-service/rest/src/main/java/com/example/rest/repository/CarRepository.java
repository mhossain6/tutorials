package com.example.rest.repository;

import com.example.rest.model.Car;
import com.example.rest.model.UUIDGenerator;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Data
@Component
public class CarRepository {

    private final Map<String, Car> cars = new ConcurrentHashMap<>();

    public Car findCar(String id) {
        return cars.get(id);
    }

    public Car addCar(Car car) {
        if (car.getId() == null) car.setId(UUIDGenerator.getUuid());
        return cars.put(car.getId(), car);
    }

    public List<Car> getCars() {
        return cars.values().stream().collect(Collectors.toList());
    }

    public Car removeCar(Car car) {
        return cars.remove(car.getId());
    }

    public Car updateCar(Car car) {
        return cars.put(car.getId(), car);
    }
}
