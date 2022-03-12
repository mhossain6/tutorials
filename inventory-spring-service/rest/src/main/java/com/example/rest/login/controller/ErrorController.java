package com.example.rest.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String error() {
        return "login";
    }

    @GetMapping(value = "/")
    public String getCars() {
        return "homepage";
    }
}
