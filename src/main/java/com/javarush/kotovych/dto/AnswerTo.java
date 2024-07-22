package com.javarush.kotovych.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerTo implements Serializable {
    private long id;
    private String text;
    private String nextQuestion;
}