package com.example.rest.controller;

import com.example.rest.model.Country;
import com.example.rest.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping(value = "/country")
    public List<Country> getCountry(@RequestParam(required = false) String countryCode) {
        return countryService.findCountry(countryCode);
    }

}
