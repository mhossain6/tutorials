package com.example.rest.repository;

import com.example.rest.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepo extends CrudRepository<Country, String> {
    @Query(value = "select * from Countries", nativeQuery = true)
    List<Country> findAll();
}
