package com.example.rest.model;

import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Component
public class UUIDGenerator {

    public static String getUuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
