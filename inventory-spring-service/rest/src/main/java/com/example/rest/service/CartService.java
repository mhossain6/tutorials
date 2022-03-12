package com.example.rest.service;

import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import com.example.rest.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public List<CarInventory> getCartItems() {
        return cartRepository.getCartItems();
    }

    public Car addToCart(CarInventory car) {
        return cartRepository.addCar(car);
    }

    public CarInventory deleteFromCart(String cartId) {
        return cartRepository.removeCar(cartId);
    }

}
