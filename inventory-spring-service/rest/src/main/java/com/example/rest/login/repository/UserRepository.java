package com.example.rest.login.repository;

import com.example.rest.login.model.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    Map<String, User> userMap = new HashMap<>();

    public List<User> getUser() {
        Collection<User> users = userMap.values();
        return users.stream().collect(Collectors.toList());
    }

    public User findUser(String username) {
        return userMap.get(username);
    }

    public UserRepository() {
        userMap.put("user", new User("user", "password", "USER"));
        userMap.put("admin", new User("admin", "password", "ADMIN"));
    }

    public User addUser(User user) {
        return userMap.put(user.getUserName(), user);
    }

    public User removeUser(User user) {
        return userMap.remove(user.getUserName());
    }
}
