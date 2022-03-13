package com.example.rest.controller;

import com.example.rest.login.repository.LoggedUser;
import com.example.rest.model.Car;
import com.example.rest.model.CarInventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/adminActions")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    InventoryController inventoryController;

    @Autowired
    CarController carController;

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping(value = "/listInventory")
    public List<CarInventory> getInventory(final HttpServletRequest request) {
        logger.info("in adminActions/listInventory/");
        logRequest(request);
        return inventoryController.getInventory();
    }

    @GetMapping(value = "/listCars")
    public List<Car> getCars(final HttpServletRequest request) {
        logger.info("in adminActions/listCars/");
        logRequest(request);
        return carController.getCars();
    }

    @PostMapping(value = "/addCar")
    public Car addCar(@RequestBody(required = true) Car inCar,
                      final HttpServletRequest request) {
        logger.info("in adminActions/addCar/");
        logRequest(request);
        return carController.putCar(inCar);
    }

    @PostMapping(value = "/delCar")
    public Car deleteCar(@RequestBody(required = true) Car car,
                         final HttpServletRequest request) {
        logger.info("in adminActions/delCar/");
        logRequest(request);
        return carController.deleteCar(car);
    }

    @PostMapping(value = "/addCarToInventory")
    public Car addCarToInventory(@RequestBody(required = true) Car inCar,
                                 final HttpServletRequest request) {
        logger.info("in adminActions/addCarToInventory/");
        logRequest(request);
        return inventoryController.addToInventory(inCar);
    }

    @PostMapping(value = "/delCarFromInventory")
    public CarInventory delCarFromInventory(@RequestBody(required = true) CarInventory inCar,
                                            final HttpServletRequest request) {
        logger.info("in adminActions/delCarFromInventory/");
        logRequest(request);
        return inventoryController.deleteFromInventory(inCar.getId());
    }

    private void logRequest(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session != null) {
            LoggedUser user = (LoggedUser) session.getAttribute("user");
            if (null != user && user.getUsername() != null) {
                logger.info("accessing url using : {} ", user.getUsername());
                logger.info("active users: {} ", user.getActiveUsers());
            }
        }

    }
}
