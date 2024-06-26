package com.javarush.kotovych.controller;

import com.javarush.kotovych.config.NanoSpring;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainPageController {

    private final QuestService questService = NanoSpring.find(QuestService.class);
    private final UserService userService = NanoSpring.find(UserService.class);

    @GetMapping(UriConstants.MAIN_PAGE_URI)
    public ModelAndView mainPage(@SessionAttribute(value = Constants.ID, required = false) String id,
                                 HttpServletRequest request) {

        SessionAttributeSetter.addSessionAttribute(request, Constants.CURRENT_PART, Constants.START);

        ModelAndView modelAndView = new ModelAndView(Constants.HOME_PAGE);

        modelAndView.addObject(Constants.QUESTS, questService.getAll());
        if(id == null){
            id = Constants.DEFAULT_ID;
            SessionAttributeSetter.addSessionAttribute(request, Constants.ID, Constants.DEFAULT_ID);
        }

        boolean exists = userService.checkIfExists(Long.parseLong(id));
        if (exists) {
            User user = userService.getIfExists(Long.parseLong(id));
            modelAndView.addObject(Constants.LOGGED_IN, true);
            modelAndView.addObject(Constants.USERNAME, user.getUsername());
        } else {
            modelAndView.addObject(Constants.LOGGED_IN, false);
            modelAndView.addObject(Constants.USERNAME, Constants.NOT_LOGGED_IN);
        }

        return modelAndView;
    }
}
