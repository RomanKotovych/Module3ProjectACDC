package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.QuestParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class QuestCreationController {

    @Autowired
    private QuestService questService;

    @Autowired
    private UserService userService;

    @GetMapping("/create-quest")
    public ModelAndView getCreateQuestPage(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        if (userService.checkIfExists(id)) {
            return new ModelAndView(Constants.CREATE_QUEST);
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }

    @PostMapping("/create-quest")
    public ModelAndView createQuest(@RequestParam(Constants.JSON) String json,
                                    @CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        User user = userService.getIfExists(id);
        if (user != null) {
            String author = user.getLogin();
            Quest quest;
            try {
                quest = QuestParser.parseFromJson(json);
                quest.setAuthor(author);
                log.info(LoggerConstants.QUEST_CREATED_LOG, quest.getName());
            } catch (Exception e) {
                log.info(Constants.FAILED_TO_CREATE_QUEST);
                ModelAndView modelAndView = new ModelAndView(Constants.CREATE_QUEST);
                modelAndView.addObject(Constants.ERROR, true);
                return modelAndView;
            }
            questService.createIfNotExists(quest);
            return new ModelAndView(Constants.REDIRECT_QUEST_NAME + quest.getName());
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
