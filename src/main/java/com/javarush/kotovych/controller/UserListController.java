package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserListController {

    @Autowired
    private UserService userService;

    @GetMapping(UriConstants.USER_LIST_URI)
    public ModelAndView getUserListPage(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        ModelAndView modelAndView = new ModelAndView(Constants.USER_LIST);
        modelAndView.addObject(Constants.USERS, userService.getAll());

        User user = userService.getIfExists(id);

        modelAndView.addObject(Constants.USERNAME, user.getLogin());
        return modelAndView;
    }
}
