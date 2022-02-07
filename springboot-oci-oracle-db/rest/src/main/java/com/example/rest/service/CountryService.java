package com.example.rest.service;


import com.example.rest.entity.Country;
import com.example.rest.repository.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepo countryRepo;

    public List<Country> findAllCountry() {
        List<Country> countries = countryRepo.findAll();
        return countries;
    }
}
