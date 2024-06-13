package com.javarush.kotovych.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.kotovych.config.RedisConfig;
import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.config.SessionCreator;
import com.javarush.kotovych.exception.AppException;
import com.javarush.kotovych.repository.QuestRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Transactional
public class QuestService extends QuestRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public QuestService() {
        super(Quest.class);
    }

    public boolean checkIfExists(String name) {
        Session session = SessionCreator.getSession();
        Query<Quest> query = session.createQuery("from Quest where name = :name", Quest.class);
        query.setParameter(Constants.NAME, name);

        return query.uniqueResult() != null;
    }

    public Quest getIfExists(long id) {
        Quest quest = getQuestFromCache(id);
        if (quest != null) {
            return quest;
        }
        quest = get(id).orElse(null);
        if (quest != null) {
            storeQuestInCache(quest);
        }
        return quest;
    }

    public Quest getIfExists(String name) {
        Session session = SessionCreator.getSession();
        Query<Long> query = session.createQuery("select e.id from Quest e where name = :name", Long.class);
        query.setParameter(Constants.NAME, name);

        Long questId = query.uniqueResult();
        return getIfExists(questId);
    }


    public void storeQuestInCache(Quest quest) {
        try {
            String questJson = objectMapper.writeValueAsString(quest);
            RedisConfig.getRedisCommands().set("quest:" + quest.getId(), questJson);
        } catch (JsonProcessingException e) {
            throw new AppException(e);
        }
    }

    public Quest getQuestFromCache(long questId) {
        String questJson = RedisConfig.getRedisCommands().get("quest:" + questId);
        if (questJson != null) {
            try {
                return objectMapper.readValue(questJson, Quest.class);
            } catch (IOException e) {
                throw new AppException(e);
            }
        }
        return null;
    }

}
