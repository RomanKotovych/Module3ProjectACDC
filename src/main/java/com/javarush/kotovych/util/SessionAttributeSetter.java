package com.javarush.kotovych.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionAttributeSetter {
    public static void addSessionAttribute(HttpServletRequest request, String name, Object value) {
        request.getSession().setAttribute(name, value);
    }
}
