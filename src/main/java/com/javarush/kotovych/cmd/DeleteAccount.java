package com.javarush.kotovych.cmd;

import com.javarush.kotovych.config.NanoSpring;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class DeleteAccount {
    private final UserService userService = NanoSpring.find(UserService.class);

    @PostMapping(UriConstants.DELETE_ACCOUNT_URI)
    public ModelAndView deleteAccount(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        userService.delete(userService.getIfExists(id));
        log.info(LoggerConstants.USER_ID_DELETED_LOG, id);
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
