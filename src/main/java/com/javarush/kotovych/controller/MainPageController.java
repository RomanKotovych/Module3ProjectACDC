package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.dto.UserTo;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final QuestService questService;
    private final UserService userService;

    @GetMapping(UriConstants.MAIN_PAGE_URI)
    public ModelAndView mainPage(@SessionAttribute(value = Constants.USER, required = false) UserTo user,
                                 HttpServletRequest request) {

        SessionAttributeSetter.addSessionAttribute(request, Constants.CURRENT_PART, Constants.START);

        ModelAndView modelAndView = new ModelAndView(Constants.HOME_PAGE);

        modelAndView.addObject(Constants.QUESTS, questService.getAll());


        if (user == null || !userService.existsById(user.getId())) {
            modelAndView.addObject(Constants.LOGGED_IN, false);
        } else {
            modelAndView.addObject(Constants.LOGGED_IN, true);
            modelAndView.addObject(Constants.USER, user);
        }

        return modelAndView;
    }
}
