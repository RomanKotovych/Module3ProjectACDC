package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.util.CookieSetter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LogOut {

    @PostMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        CookieSetter.addCookie(response, Constants.ID, Constants.DEFAULT_ID);
        log.info(LoggerConstants.USER_LOGGED_OUT_LOG);
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
