package com.javarush.kotovych.service;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.controller.MainPageController;
import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import com.javarush.kotovych.util.QuestParser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
public class QuestService extends QuestRepository {




    public QuestService() {
        super(Quest.class);
    }

//    @PostConstruct
//    public void init() {
//        addDefaultQuests();
//    }


    private void addDefaultQuests() {
        for (String path : Constants.DEFAULT_QUESTS) {
            Quest quest = QuestParser.parseFromJsonFile(MainPageController.class.getResource(path));
            create(quest);
        }
    }


    public boolean checkIfExists(String name) {
        try (Session session = sessionFactory.createSession()) {
            Query<Quest> query = session.createQuery("from Quest where name = :name", Quest.class);
            query.setParameter(Constants.NAME, name);

            return query.uniqueResult() != null;
        }
    }


    public Quest getIfExists(long id) {
        return get(id).orElse(new Quest());
    }

    public Quest getIfExists(String name) {
        try (Session session = sessionFactory.createSession()) {
            Query<Quest> query = session.createQuery("from Quest where name = :name", Quest.class);
            query.setParameter(Constants.NAME, name);

            Quest quest = query.uniqueResult();
            return quest != null ? quest : new Quest();
        }
    }

}
