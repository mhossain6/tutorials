package com.example.rest.service;

import com.example.rest.model.Country;
import com.example.rest.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> findCountry(String name) {

        if (null != name) {
            List<Country> countries = new ArrayList<>();
            countries.add(countryRepository.findCountry(name));
            return countries;
        }

        return countryRepository.getCountries().values().stream().collect(Collectors.toList());
    }
}
