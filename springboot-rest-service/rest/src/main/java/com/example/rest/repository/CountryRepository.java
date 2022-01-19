package com.example.rest.repository;

import com.example.rest.model.Country;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class CountryRepository {

    private final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCountryCode("ES");

        countries.put(spain.getCountryCode(), spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCountryCode("PL");


        countries.put(poland.getCountryCode(), poland);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCountryCode("UK");

        countries.put(uk.getCountryCode(), uk);
    }

    public Country findCountry(String getCountryCode) {
        Assert.notNull(getCountryCode, "The country's name must not be null");
        return countries.get(getCountryCode);
    }
}
