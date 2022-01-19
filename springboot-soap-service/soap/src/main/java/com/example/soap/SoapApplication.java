package com.example.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.soap",
        "com.example.soap.config",
        "com.example.soap.controller",
        "com.example.soap.service",
        "com.example.soap.schema",
        "com.example.soap.repository"})
@SpringBootApplication
public class SoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapApplication.class, args);
    }

}
