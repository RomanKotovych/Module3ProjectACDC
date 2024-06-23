package com.javarush.kotovych.filter;


import com.javarush.kotovych.config.SessionCreator;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.FilterUrlChecker;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;

import java.io.IOException;

@Component
@WebFilter(urlPatterns = UriConstants.ALL_URIS)
@Slf4j
public class LoggedInFilter implements Filter {

    private final SessionCreator sessionCreator;
    private final UserService userService;

    @Autowired
    public LoggedInFilter(SessionCreator sessionCreator, UserService userService) {
        this.sessionCreator = sessionCreator;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        log.info(LoggerConstants.REQUEST_TO_LOG, req.getMethod(), uri);

        if (FilterUrlChecker.isAllowed(uri)) {
            String idSessionValue = (String) req.getSession().getAttribute(Constants.ID);
            if(idSessionValue == null){
                idSessionValue = Constants.DEFAULT_ID;
                SessionAttributeSetter.addSessionAttribute(req, Constants.ID, Constants.DEFAULT_ID);
            }


            long id = Long.parseLong(idSessionValue);

            if (!userService.checkIfExists(id)) {
                resp.sendRedirect(UriConstants.MAIN_PAGE_URI);
                return;
            }
        }
        sessionCreator.beginTransactional();
        filterChain.doFilter(servletRequest, servletResponse);
        sessionCreator.endTransactional();
    }
}
