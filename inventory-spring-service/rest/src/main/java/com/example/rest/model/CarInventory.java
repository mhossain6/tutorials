package com.example.rest.model;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CarInventory extends Inventory<Car> {

    public CarInventory(String id, Car car) {
        super(id, car);
    }
}
