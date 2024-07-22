package com.javarush.kotovych.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionTo implements Serializable {
    private long id;
    private String name;
    private String situation;
    private String text;

    @ToString.Exclude
    private List<AnswerTo> answers;
}