package com.javarush.kotovych.entity;

import com.javarush.kotovych.converter.MapStringQuestionConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "quest", schema = "public")
public class Quest {
    @Column
    private String name;
    @Column
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Convert(converter = MapStringQuestionConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Question> questions;

    @Column
    private String author;
}
