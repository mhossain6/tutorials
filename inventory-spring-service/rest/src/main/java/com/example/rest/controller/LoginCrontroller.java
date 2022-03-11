package com.example.rest.controller;

import com.example.rest.model.User;
import com.example.rest.service.LoginServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class LoginCrontroller {
    @Autowired
    LoginServie loginServie;

    @PostMapping(value = "/login")
    public String login(@RequestBody User user) {
        return loginServie.login(user.getUserName(), user.getPassword());
    }
}
