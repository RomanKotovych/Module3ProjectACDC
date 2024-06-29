package com.javarush.kotovych.filter;


import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.FilterUrlChecker;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Setter
@Component
@WebFilter(urlPatterns = UriConstants.ALL_URIS)
@Slf4j
public class LoggedInFilter extends HttpFilter {

    private UserService userService;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        log.info(LoggerConstants.REQUEST_TO_LOG, req.getMethod(), uri);

        Object idSessionValue = req.getSession().getAttribute(Constants.ID);
        long longIdSessionValue;
        if (idSessionValue == null) {
            longIdSessionValue = Constants.DEFAULT_ID;
        } else {
            longIdSessionValue = Long.parseLong((String) idSessionValue);
        }

        if (FilterUrlChecker.isFilterValid(uri)) {
            if (!userService.existsById(longIdSessionValue)) {
                resp.sendRedirect(UriConstants.MAIN_PAGE_URI);
                return;
            }
        }
        filterChain.doFilter(req, resp);
    }

}
