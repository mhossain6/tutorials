package com.example.restclient.Controller;

import com.example.restclient.Client.HTTPRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RestClientController {

    @Autowired
    HTTPRestClient restClient;

    @GetMapping("/InvokeClient")
    public void invokeClient() {
        try {
            restClient.callRestService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test")
    public String invokeTest() {
        return "Hello World!";
    }
}
