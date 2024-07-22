package com.javarush.kotovych.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QuestTo implements Serializable {
    private long id;
    private String name;
    private String description;

    @ToString.Exclude
    private List<QuestionTo> questions;
    private UserTo author;
}