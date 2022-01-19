package com.example.soap.service;

import com.example.soap.repository.CountryRepository;
import com.example.soap.schema.Country;
import com.example.soap.schema.GetCountryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public Country findCountry(GetCountryRequest request) {
        String name = request.getName();
        Assert.notNull(name, "The country's name must not be null");
        return countryRepository.findCountry(name);
    }
}
