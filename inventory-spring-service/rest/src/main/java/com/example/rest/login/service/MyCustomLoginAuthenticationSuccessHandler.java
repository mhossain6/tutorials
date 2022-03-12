package com.example.rest.login.service;

import com.example.rest.login.model.User;
import com.example.rest.login.repository.ActiveUserStore;
import com.example.rest.login.repository.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("myAuthenticationSuccessHandler")
public class MyCustomLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    ActiveUserStore activeUserStore;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {

        String userName = null;
        if (authentication.getPrincipal() instanceof User) {
            userName = ((User)authentication.getPrincipal()).getUserName();
        }
        else {
            userName = authentication.getName();
        }

        if (userName.compareToIgnoreCase("user") == 0) {
            redirectStrategy.sendRedirect(request, response, "/homepage.html?user=" + userName);
        } else {
            redirectStrategy.sendRedirect(request, response, "/homepage.html?user=" + userName);
        }

        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(15 * 60);

            LoggedUser user = new LoggedUser(userName, activeUserStore);
            session.setAttribute("user", user);
        }
        clearAuthenticationAttributes(request);

    }

    protected void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}