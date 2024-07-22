package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.dto.UserTo;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserListController {

    private final UserService userService;


    @GetMapping(UriConstants.USER_LIST_URI)
    public ModelAndView getUserListPage(@SessionAttribute(value = Constants.USER) UserTo user) {
        ModelAndView modelAndView = new ModelAndView(Constants.USER_LIST);
        modelAndView.addObject(Constants.USERS, userService.getAll());


        modelAndView.addObject(Constants.USER, user);
        return modelAndView;
    }
}
