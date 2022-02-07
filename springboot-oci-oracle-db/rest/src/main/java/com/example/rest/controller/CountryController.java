package com.example.rest.controller;

import com.example.rest.entity.Country;
import com.example.rest.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class CountryController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CountryService countryService;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }


    @GetMapping(value = "/country")
    public List<Country> getCountry() {

        logger.info("in Country method");
        List<Country> countries = countryService.findAllCountry();
        logger.info("got {} countries", countries);
        return countries;
    }



}
