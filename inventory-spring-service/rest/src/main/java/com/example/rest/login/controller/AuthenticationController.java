package com.example.rest.login.controller;

import com.example.rest.login.repository.LoggedUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
public class AuthenticationController {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getLogin(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model, @RequestParam("error") final Optional<String> error) throws IOException {

        final HttpSession session = request.getSession(false);

        if (session != null) {
            LoggedUser user = (LoggedUser) session.getAttribute("user");
            if (null != user && user.getUsername() != null)
                redirectStrategy.sendRedirect(request, response, "/homepage.html?user=" + user.getUsername());
            //return new ModelAndView("homepage", model);
        }

        return new ModelAndView("login", model);
    }

    @GetMapping("/logout")
    public ModelAndView logout(final HttpServletRequest request, final ModelMap model) {

        return new ModelAndView("logout", model);
    }
}
