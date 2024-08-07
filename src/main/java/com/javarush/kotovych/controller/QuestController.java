package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.dto.QuestTo;
import com.javarush.kotovych.dto.QuestionTo;
import com.javarush.kotovych.dto.UserTo;
import com.javarush.kotovych.entity.Question;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;
    private final UserService userService;

    @GetMapping(UriConstants.QUEST_URI)
    public ModelAndView showQuest(@RequestParam(value = Constants.NAME) String questName,
                                  @SessionAttribute(name = Constants.CURRENT_PART, required = false) String currentPart,
                                  @SessionAttribute(value = Constants.USER) UserTo user,
                                  HttpServletRequest request) {
        if (currentPart == null || questName == null) {
            return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
        }

        setStatistics(user, currentPart);
        SessionAttributeSetter.addSessionAttribute(request, Constants.NAME, questName);

        QuestTo quest = questService.get(questName);
        if (quest != null) {
            ModelAndView modelAndView = new ModelAndView(chooseTemplate(currentPart));
            addRequiredObjects(modelAndView, quest, user, currentPart);

            return modelAndView;
        }

        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }

    @PostMapping(UriConstants.QUEST_URI)
    public ModelAndView changeCurrentPart(@RequestParam(value = Constants.CURRENT_PART, required = false) String currentPart,
                                          @SessionAttribute(value = Constants.NAME, required = false) String questName,
                                          HttpServletRequest request) {
        if (questName == null || currentPart == null) {
            return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
        }

        SessionAttributeSetter.addSessionAttribute(request, Constants.CURRENT_PART, currentPart);
        return new ModelAndView(Constants.REDIRECT_QUEST_NAME + questName);
    }

    private String chooseTemplate(String currentPart) {
        if (currentPart.startsWith(Constants.WIN)) {
            return Constants.WIN_TEMPLATE;
        } else if (currentPart.startsWith(Constants.GAME_OVER)) {
            return Constants.GAME_OVER_TEMPLATE;
        } else {
            return Constants.QUEST_TEMPLATE;
        }
    }

    private void setStatistics(UserTo user, String currentPart) {
        if (currentPart.startsWith(Constants.WIN)) {
            int wins = user.getWins();
            user.setWins(wins + 1);
            userService.update(user);
        } else if (currentPart.startsWith(Constants.GAME_OVER)) {
            int losses = user.getLosses();
            user.setLosses(losses + 1);
            userService.update(user);
        }
    }

    private void addRequiredObjects(ModelAndView modelAndView,
                                    QuestTo quest,
                                    UserTo user,
                                    String currentPart) {
        QuestionTo question = quest.getQuestions().stream()
                .filter(o -> o.getName().equals(currentPart))
                .findFirst().orElse(null);

        modelAndView.addObject(Constants.QUEST, quest);
        modelAndView.addObject(Constants.QUESTION, question);
        modelAndView.addObject(Constants.AUTHOR, user.getId() == quest.getAuthor().getId());
    }
}
