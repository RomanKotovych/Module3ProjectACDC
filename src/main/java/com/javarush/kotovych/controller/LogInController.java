package com.javarush.kotovych.controller;

import com.javarush.kotovych.config.NanoSpring;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class LogInController {

    private final UserService userService = NanoSpring.find(UserService.class);


    @GetMapping(UriConstants.LOGIN_URI)
    public ModelAndView getLoginPage() {
        return new ModelAndView(Constants.LOGIN);
    }

    @PostMapping(UriConstants.LOGIN_URI)
    public ModelAndView logIn(@RequestParam(Constants.USERNAME) String username,
                              @RequestParam(Constants.PASSWORD) String password,
                              HttpServletRequest request) {
        ModelAndView loginPage = new ModelAndView(Constants.LOGIN);
        if (!userService.checkIfCorrect(username, password)) {
            log.info(LoggerConstants.USER_NOT_FOUND_LOG, username);
            loginPage.addObject(Constants.ERROR, Constants.USER_NOT_FOUND_OR_INCORRECT_PASSWORD);
            log.info(LoggerConstants.USER_NOT_FOUND_LOG, username);
            return loginPage;
        }
        User user = userService.getIfExists(username);
        if (user != null) {
            long id = user.getId();
            SessionAttributeSetter.addSessionAttribute(request, Constants.ID, String.valueOf(id));
            log.info(LoggerConstants.USER_LOGGED_IN_LOG, username);
            return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
        }
        return new ModelAndView(Constants.LOGIN);
    }
}
