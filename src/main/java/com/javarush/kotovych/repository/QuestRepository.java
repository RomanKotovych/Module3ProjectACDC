package com.javarush.kotovych.repository;

import com.javarush.kotovych.entity.Quest;

public class QuestRepository extends BaseRepository<Quest> {
    public QuestRepository(Class<Quest> entityClass) {
        super(entityClass);
    }
}
