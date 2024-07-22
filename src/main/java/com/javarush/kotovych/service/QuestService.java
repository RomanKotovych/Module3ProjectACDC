package com.javarush.kotovych.service;

import com.javarush.kotovych.dto.QuestTo;
import com.javarush.kotovych.mapping.Dto;
import com.javarush.kotovych.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class QuestService {

    private final QuestRepository questRepository;

    public void update(QuestTo entity) {
        questRepository.save(Dto.MAPPER.toEntity(entity));
    }

    public QuestTo get(String name){
        return Dto.MAPPER.toDto(questRepository.getByName(name));
    }

    public void delete(QuestTo entity) {
        questRepository.delete(Dto.MAPPER.toEntity(entity));
    }

    public QuestTo get(long id) {
        return Dto.MAPPER.toDto(questRepository.getReferenceById(id));
    }


    public void create(QuestTo quest){
        questRepository.save(Dto.MAPPER.toEntity(quest));
    }

    public List<QuestTo> getAll(){
        return questRepository.findAll().stream()
                .map(Dto.MAPPER::toDto)
                .toList();
    }
}
