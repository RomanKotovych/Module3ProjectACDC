package com.javarush.kotovych.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "answer", schema = "public")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String text;

    @Column
    private String nextQuestion;
}
