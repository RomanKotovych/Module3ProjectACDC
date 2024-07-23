package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.constants.UriConstants;
import com.javarush.kotovych.dto.UserTo;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeleteAccount {
    private final UserService userService;

    @PostMapping(UriConstants.DELETE_ACCOUNT_URI)
    public ModelAndView deleteAccount(@SessionAttribute(value = Constants.USER) UserTo user,
                                      HttpServletRequest request) {
        userService.deleteById(user.getId());
        SessionAttributeSetter.addSessionAttribute(request, Constants.ID, String.valueOf(Constants.DEFAULT_ID));
        log.info(LoggerConstants.USER_ID_DELETED_LOG, user.getId());
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
