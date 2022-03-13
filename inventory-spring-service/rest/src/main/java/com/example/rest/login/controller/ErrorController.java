package com.example.rest.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String error() {
        return "login";
    }

    @GetMapping(value = "/")
    public ModelAndView homepage(final HttpServletRequest request, final ModelMap model) {

        return new ModelAndView("homepage", model);
    }

    @GetMapping(value = "/invalidSession")
    public String invalidSession() {
        return "invalidSession";
    }
    @GetMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
