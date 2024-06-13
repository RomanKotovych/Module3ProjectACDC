package com.javarush.kotovych.util;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.UriConstants;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilterUrlChecker {
    public static boolean isAllowed(String url) {
        return Constants.NOT_FILTER_URLS.stream()
                .noneMatch(url::startsWith);
    }

    public static boolean isTransactional(String url){
        return UriConstants.TRANSACTION_URIS.stream()
                .anyMatch(url::startsWith);
    }
}
