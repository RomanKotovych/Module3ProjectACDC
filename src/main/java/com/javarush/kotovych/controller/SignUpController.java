package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.CookieSetter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public ModelAndView getSignupPage() {
        return new ModelAndView(Constants.SIGNUP);
    }

    @PostMapping("/signup")
    public ModelAndView signUp(@RequestParam(Constants.USERNAME) String username,
                               @RequestParam(Constants.PASSWORD) String password,
                               HttpServletResponse response) {

        if(!userService.checkIfExists(username)){
            User user = new User(username, password);
            userService.create(user);
            long id = user.getId();
            CookieSetter.addCookie(response, Constants.ID, String.valueOf(id));

            log.info(Constants.USER_WITH_USERNAME_CREATED, username);
            return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
        } else {
            log.info(Constants.FAILED_TO_CREATE_USER_BECAUSE_IT_ALREADY_EXISTS);
            ModelAndView modelAndView = new ModelAndView(Constants.SIGNUP);
            modelAndView.addObject(Constants.ERROR, Constants.FAILED_TO_CREATE_USER_BECAUSE_IT_ALREADY_EXISTS);
            return modelAndView;
        }
    }
}
