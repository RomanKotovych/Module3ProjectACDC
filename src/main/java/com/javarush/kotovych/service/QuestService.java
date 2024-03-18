package com.javarush.kotovych.service;

import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public class QuestService implements Service {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }
    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public Optional<Quest> get(long id) {
        return questRepository.get(id);
    }
}
