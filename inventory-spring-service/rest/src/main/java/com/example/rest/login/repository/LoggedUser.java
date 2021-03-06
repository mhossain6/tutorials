package com.example.rest.login.repository;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;


@Component
public class LoggedUser implements HttpSessionBindingListener {

    private String username;
    private ActiveUserStore activeUserStore;

    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
        this.activeUserStore.getUsers().add(username);
    }

    public LoggedUser() {
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getUsername())) {
            users.add(user.getUsername());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (null != users && user != null)
            users.remove(user.getUsername());
    }

    public List<String> getActiveUsers() {
        return activeUserStore.getUsers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
