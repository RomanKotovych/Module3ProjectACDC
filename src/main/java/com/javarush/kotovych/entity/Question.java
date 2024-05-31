package com.javarush.kotovych.entity;

import com.javarush.kotovych.factory.SessionCreator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question", schema = "public")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String situation;

    @Column
    private String text;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

}
