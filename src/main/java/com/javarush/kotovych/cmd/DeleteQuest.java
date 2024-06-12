package com.javarush.kotovych.cmd;

import com.javarush.kotovych.config.NanoSpring;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.service.QuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class DeleteQuest {

    private final QuestService questService;

    public DeleteQuest(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping(UriConstants.DELETE_QUEST_URI)
    public ModelAndView deleteQuest(@RequestParam(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        questService.delete(questService.getIfExists(id));
        log.info(LoggerConstants.QUEST_ID_DELETED_LOG, id);
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
