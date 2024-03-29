package com.javarush.kotovych.service;

import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QuestServiceIT {

    private QuestRepository questRepository;

    private QuestService questService;

    @BeforeEach
    public void setUp() {
        questRepository = mock(QuestRepository.class);
        questService = new QuestService(questRepository);
    }

    @Test
    public void givenQuest_whenCreateQuestCalled_thenQuestIsPersisted() {
        Quest quest = new Quest();
        questService.create(quest);
        verify(questRepository, times(1)).create(quest);
    }

    @Test
    public void givenQuest_whenUpdateQuestCalled_thenQuestIsUpdated() {
        Quest quest = new Quest();
        questService.update(quest);
        verify(questRepository, times(1)).update(quest);
    }

    @Test
    public void givenQuest_whenDeleteQuestCalled_thenQuestIsDeleted() {
        Quest quest = new Quest();
        questService.delete(quest);
        verify(questRepository, times(1)).delete(quest);
    }

    @Test
    public void whenGetAllQuestsCalled_thenAllQuestsAreReturned() {
        questService.getAll();
        verify(questRepository, times(1)).getAll();
    }

    @Test
    public void givenQuestId_whenGetQuestCalled_thenQuestIsReturned() {
        long id = 1;
        questService.get(id);
        verify(questRepository, times(1)).get(id);
    }

    @Test
    public void givenQuestName_whenGetQuestCalled_thenQuestIsReturned() {
        String name = "testQuest";
        questService.get(name);
        verify(questRepository, times(1)).get(name);
    }

    @Test
    public void givenQuest_whenCreateIfNotExistsCalled_thenQuestIsCreatedIfNotExists() {
        Quest quest = new Quest();
        when(questRepository.get(quest.getName())).thenReturn(Optional.empty());
        questService.createIfNotExists(quest);
        verify(questRepository, times(1)).create(quest);
    }

    @Test
    public void givenQuest_whenCreateIfNotExistsCalled_thenNothingHappensIfQuestExists() {
        Quest quest = new Quest();
        when(questRepository.get(quest.getName())).thenReturn(Optional.of(quest));
        questService.createIfNotExists(quest);
        verify(questRepository, times(0)).create(quest);
    }

    @Test
    public void givenQuest_whenDeleteQuestIdCalled_thenQuestIsDeleted() {
        long id = 1;
        Quest quest = new Quest();
        when(questRepository.get(id)).thenReturn(Optional.of(quest));
        questService.delete(id);
        verify(questRepository, times(1)).delete(quest);
    }

    @Test
    public void givenQuest_whenDeleteQuestCalled_thenNothingHappensIfQuestDoesNotExist() {
        long id = 1;
        when(questRepository.get(id)).thenReturn(Optional.empty());
        questService.delete(id);
        verify(questRepository, times(0)).delete(any());
    }

    @Test
    public void givenQuestId_whenGetQuestIfExistsCalled_thenQuestIsReturned() {
        long id = 1;
        Quest quest = new Quest();
        when(questRepository.get(id)).thenReturn(Optional.of(quest));
        Quest result = questService.getIfExists(id);
        Assertions.assertEquals(quest, result);
    }

    @Test
    public void givenQuestId_whenGetQuestIfExistsCalled_thenNullIsReturned() {
        long id = 1;
        when(questRepository.get(id)).thenReturn(Optional.empty());
        Quest result = questService.getIfExists(id);
        Assertions.assertNull(result);
    }

    @Test
    public void givenQuestName_whenGetQuestIfExistsCalled_thenQuestIsReturned() {
        String name = "testQuest";
        Quest quest = new Quest();
        when(questRepository.get(name)).thenReturn(Optional.of(quest));
        Quest result = questService.getIfExists(name);
        Assertions.assertEquals(quest, result);
    }

    @Test
    public void givenQuestName_whenGetQuestIfExistsCalled_thenNullIsReturned() {
        String name = "testQuest";
        when(questRepository.get(name)).thenReturn(Optional.empty());
        Quest result = questService.getIfExists(name);
        Assertions.assertNull(result);
    }
}