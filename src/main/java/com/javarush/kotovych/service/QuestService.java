package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import com.javarush.kotovych.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class QuestService implements Repository<Quest> {

    private final QuestRepository questRepository;

    @Override
    @Cacheable("quests")
    public void update(Quest entity) {
        questRepository.save(entity);
    }

    @Cacheable("quests")
    public Quest get(String name) {
        return questRepository.getByName(name);
    }

    @Override
    @CacheEvict("quests")
    public void delete(Quest entity) {
        questRepository.delete(entity);
    }

    @Cacheable("quests")
    public Quest get(long id) {
        return questRepository.getReferenceById(id);
    }


    @CachePut("quests")
    public void create(Quest quest) {
        questRepository.save(quest);
    }

    public List<Quest> getAll() {
        return questRepository.findAll();
    }
}
