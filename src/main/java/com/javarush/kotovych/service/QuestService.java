package com.javarush.kotovych.service;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.factory.SessionCreator;
import com.javarush.kotovych.repository.QuestRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
public class QuestService extends QuestRepository {

    public QuestService() {
        super(Quest.class);
    }

    public boolean checkIfExists(String name) {
        try (Session session = SessionCreator.createSession()) {
            Query<Quest> query = session.createQuery("from Quest where name = :name", Quest.class);
            query.setParameter(Constants.NAME, name);

            return query.uniqueResult() != null;
        }
    }


    public Quest getIfExists(long id) {
        return get(id).orElse(new Quest());
    }

    public Quest getIfExists(String name) {
        try (Session session = SessionCreator.createSession()) {
            Query<Quest> query = session.createQuery("from Quest where name = :name", Quest.class);
            query.setParameter(Constants.NAME, name);

            Quest quest = query.uniqueResult();
            return quest != null ? quest : new Quest();
        }
    }

}
