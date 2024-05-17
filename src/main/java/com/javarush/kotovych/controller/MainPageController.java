package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private QuestService questService;

    @Autowired
    private UserService userService;

    @GetMapping(UriConstants.MAIN_PAGE_URI)
    public ModelAndView mainPage(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id,
                                 HttpServletRequest request) {

        request.getSession().invalidate();
        SessionAttributeSetter.addSessionAttribute(request, Constants.CURRENT_PART, Constants.START);

        ModelAndView modelAndView = new ModelAndView(Constants.HOME_PAGE);

        List<Quest> fakeQuests = new ArrayList<>();
        modelAndView.addObject(Constants.QUEST, fakeQuests);

        boolean exists = userService.checkIfExists(id);
        if (exists) {
            User user = userService.getIfExists(id);
            modelAndView.addObject(Constants.LOGGED_IN, true);
            modelAndView.addObject(Constants.USERNAME, user.getUsername());
        } else {
            modelAndView.addObject(Constants.LOGGED_IN, false);
            modelAndView.addObject(Constants.USERNAME, Constants.NOT_LOGGED_IN);
        }

        return modelAndView;
    }
}
