package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteAccount {
    @Autowired
    UserService userService;

    @PostMapping("/delete-account")
    public ModelAndView deleteAccount(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        userService.delete(id);
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
