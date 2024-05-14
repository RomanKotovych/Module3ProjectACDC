package com.javarush.kotovych.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.exception.AppException;
import com.javarush.kotovych.entity.Quest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;


@Slf4j
@UtilityClass
public class QuestParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Quest parseFromJson(String json) {
        try {
            return objectMapper.readValue(json, Quest.class);
        } catch (Exception e) {
            log.debug(Constants.COULD_NOT_PARSE_QUEST_JSON, e);
            throw new AppException(e);
        }
    }

    public static Quest parseFromJsonFile(URL url) {
        try {
            return objectMapper.readValue(url, Quest.class);
        } catch (Exception e) {
            log.debug(Constants.COULD_NOT_PARSE_QUEST_JSON, e);
            throw new AppException(e);
        }
    }
}
