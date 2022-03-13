package com.example.rest.controller;

import com.example.rest.login.repository.LoggedUser;
import com.example.rest.model.CarInventory;
import com.example.rest.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/buyerActions")
@CrossOrigin("*")
public class BuyerController {

    @Autowired
    InventoryController inventoryController;

    Logger logger = LoggerFactory.getLogger(BuyerController.class);

    @Autowired
    CartService cartService;

    @GetMapping(value = "/listInventory")
    public List<CarInventory> getInventory(
            final HttpServletRequest request) {
        logger.info("in userActions/listInventory/");
        logRequest(request);
        return inventoryController.getInventory();
    }

    @GetMapping(value = "/listCartItems")
    public List<CarInventory> listCartItems(
            final HttpServletRequest request) {
        logger.info("in userActions/listCartItems/");
        logRequest(request);
        return cartService.getCartItems();
    }

    @GetMapping(value = "/removeCartItem/{inventoryId}")
    public List<CarInventory> removeCartItem(@RequestParam("inventoryId") String inventoryId,
                                             final HttpServletRequest request) {
        logger.info("in userActions/removeCartItem/");
        logRequest(request);
        return cartService.removeCartItem(inventoryId);
    }

    @GetMapping(value = "/addItemToCart/{inventoryId}")
    public List<CarInventory> addItemToCart(@RequestParam("inventoryId") String inventoryId,
                                            final HttpServletRequest request) {
        logger.info("in userActions/addItemToCart/");
        logRequest(request);
        return cartService.putItemIntoCart(inventoryId);
    }

    @GetMapping(value = "/checkout")
    public void checkout(
            final HttpServletRequest request) {
        logger.info("in userActions/checkout/");
        logRequest(request);
        logger.info("Checking out cart...");
        cartService.checkOut();
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
