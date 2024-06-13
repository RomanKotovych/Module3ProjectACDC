package com.javarush.kotovych.controller;

import com.javarush.kotovych.config.NanoSpring;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;

@Slf4j
@Controller
public class EditUserController {
    private final UserService userService = NanoSpring.find(UserService.class);


    @GetMapping(UriConstants.EDIT_USER_URI)
    public ModelAndView getEditUserPage(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        ModelAndView modelAndView = new ModelAndView(Constants.EDIT_USER);
        User user = userService.getIfExists(id);
        log.info(LoggerConstants.USER_EDITS_ACCOUNT_LOG, id);
        modelAndView.addObject(Constants.USER, user);
        return modelAndView;
    }

    @PostMapping(UriConstants.EDIT_USER_URI)
    public ModelAndView editUser(@RequestParam(Constants.USERNAME) String editUsername,
                                 @RequestParam(Constants.PASSWORD) String editPassword,
                                 @CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {

        ModelAndView editPage = new ModelAndView(UriConstants.EDIT_USER_REDIRECT);


        if (editUsername.isBlank() || editPassword.isBlank()) {
            return editPage;
        }

        User user = userService.getIfExists(id);
        if (user.getLastUpdated() == null || System.currentTimeMillis() - user.getLastUpdated().getEpochSecond() * 1000 > Constants.EDITING_WAITING_TIME) {
            user.setUsername(editUsername);
            user.setPassword(editPassword);
            user.setLastUpdated(Instant.now());
            userService.update(user);
            log.info(LoggerConstants.ACCOUNT_UPDATED_LOG, id);
            return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
        } else {
            ModelAndView modelAndView = new ModelAndView(UriConstants.EDIT_USER_REDIRECT);
            modelAndView.addObject(Constants.ERROR, Constants.YOU_CAN_ONLY_EDIT_ACCOUNT_ONCE_IN_10_MINUTES);
            return modelAndView;
        }
    }
}
