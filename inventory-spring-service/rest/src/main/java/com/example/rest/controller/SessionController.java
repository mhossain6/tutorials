package com.example.rest.controller;

import com.example.rest.login.repository.LoggedUser;
import com.example.rest.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SessionController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/getSessionRole")
    public String getInventory(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session != null) {
            LoggedUser user = (LoggedUser) session.getAttribute("user");
            if (null != user && user.getUsername() != null) {
                String username = user.getUsername();
                return userRepository.findUser(username).getRole();
            }
        }
        return "USER";
    }
}
