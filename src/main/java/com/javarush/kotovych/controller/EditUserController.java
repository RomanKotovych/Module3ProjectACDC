package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.dto.UserTo;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EditUserController {
    private final UserService userService;


    @GetMapping(UriConstants.EDIT_USER_URI)
    public ModelAndView getEditUserPage(@SessionAttribute(value = Constants.USER, required = false) UserTo user) {
        ModelAndView modelAndView = new ModelAndView(Constants.EDIT_USER);
        log.info(LoggerConstants.USER_EDITS_ACCOUNT_LOG, user.getId());
        modelAndView.addObject(Constants.USER, user);
        return modelAndView;
    }

    @PostMapping(UriConstants.EDIT_USER_URI)
    public ModelAndView editUser(@RequestParam(Constants.USERNAME) String editUsername,
                                 @RequestParam(Constants.PASSWORD) String editPassword,
                                 @SessionAttribute(value = Constants.USER) UserTo user) {

        ModelAndView editPage = new ModelAndView(UriConstants.EDIT_USER_REDIRECT);


        if (editUsername.isBlank() || editPassword.isBlank()) {
            return editPage;
        }

        user.setUsername(editUsername);
        user.setPassword(editPassword);
        userService.update(user);
        log.info(LoggerConstants.ACCOUNT_UPDATED_LOG, user.getId());
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
