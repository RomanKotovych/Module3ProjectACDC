package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import com.javarush.kotovych.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class QuestService implements Repository<Quest> {

    private final QuestRepository questRepository;

    @Override
    public void update(Quest entity) {
        questRepository.save(entity);
    }

    public Quest get(String name){
        return questRepository.getByName(name);
    }

    @Override
    public void delete(Quest entity) {
        questRepository.delete(entity);
    }

    public Quest get(long id) {
        return questRepository.getReferenceById(id);
    }


    public void create(Quest quest){
        questRepository.save(quest);
    }

    public List<Quest> getAll(){
        return questRepository.findAll();
    }
}
