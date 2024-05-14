package com.javarush.kotovych.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "quests", schema = "game")
public class Quest {
    @Column
    private String name;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private Map<String, Question> questions;

    private String author;
}
