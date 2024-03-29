package com.javarush.kotovych.util;

import com.javarush.kotovych.constants.Constants;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilterUrlChecker {
    public static boolean isAllowed(String url){
        return Constants.NOT_FILTER_URLS.stream()
                .noneMatch(url::startsWith);
    }
}
