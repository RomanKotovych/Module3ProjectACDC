package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LogOut {

    @PostMapping(UriConstants.LOGOUT_URI)
    public ModelAndView logout(HttpServletRequest request) {
        SessionAttributeSetter.addSessionAttribute(request, Constants.ID, Constants.DEFAULT_ID);
        log.info(LoggerConstants.USER_LOGGED_OUT_LOG);
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
