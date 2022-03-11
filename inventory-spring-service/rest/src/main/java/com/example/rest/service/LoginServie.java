package com.example.rest.service;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServie {
    @Autowired
    UserRepository userRepository;

    public String login(String userName, String passWord) {

        List<User> users = userRepository.getUser().stream().filter(user -> user.getUserName().compareToIgnoreCase(userName) == 0).collect(Collectors.toList());
        if (users.size() > 0 && users.get(0).getPassword().compareToIgnoreCase(passWord) == 0) {
            return "Success";
        }
        return "Failed";
    }
}
