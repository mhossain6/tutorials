package com.example.rest.repository;

import com.example.rest.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void initRepository() {
        userMap.put("user1", new User("user", "password", "USER"));
        userMap.put("user2", new User("user2", "password2", "USER"));
        userMap.put("admin", new User("admin", "admin", "ADMIN"));
    }

    public User addUser(User user) {
        return userMap.put(user.getUserName(), user);
    }

    public User removeUser(User user) {
        return userMap.remove(user.getUserName());
    }
}
