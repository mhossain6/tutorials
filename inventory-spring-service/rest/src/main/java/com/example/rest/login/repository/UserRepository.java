package com.example.rest.login.repository;

import com.example.rest.login.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    Map<String, User> userMap = new ConcurrentHashMap<>();

    PasswordEncoder encoder;

    public UserRepository() {
        encoder = new BCryptPasswordEncoder(11);
        userMap.put("user", new User("user", encoder.encode("password"), "USER"));
        userMap.put("admin", new User("admin", encoder.encode("password"), "ADMIN"));
    }

    public List<User> getUser() {
        Collection<User> users = userMap.values();
        return users.stream().collect(Collectors.toList());
    }

    public User findUser(String username) {
        return userMap.get(username);
    }

    public User addUser(User user) {
        return userMap.put(user.getUserName(), user);
    }

    public User removeUser(User user) {
        return userMap.remove(user.getUserName());
    }
}
