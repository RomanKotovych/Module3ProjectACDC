package com.javarush.kotovych.filter;


import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;

import java.io.IOException;
import java.util.Arrays;

@Component
@WebFilter(urlPatterns = {"/quest", "/create-quest", "/edit-user",
        "user-list", "/delete-account", "/delete-quest", "/logout", "/try-again"})
public class LoggedInFilter implements Filter{

    @Autowired
    UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        String cookieName = Constants.ID;

        String id = Arrays.stream(cookies).
                filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst().
                orElse(new Cookie("not found", "not found"))
                .getValue();

        if(!userService.checkIfExists(id)) {
            resp.sendRedirect("/");
        }
    }
}
