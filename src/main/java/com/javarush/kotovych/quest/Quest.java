package com.javarush.kotovych.quest;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest {
    private String name;
    private String description;
    private long id;
    private Map<String, Question> questions;
    private String author;
}
