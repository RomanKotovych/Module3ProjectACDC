package com.javarush.kotovych.repository;

import com.javarush.kotovych.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface QuestRepository extends JpaRepository<Quest, Long> {
    @Query("select q from Quest q where q.name = ?1")
    Quest getByName(String name);
}
