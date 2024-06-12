package com.javarush.kotovych.entity;

import com.javarush.kotovych.service.UserService;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.io.Serializable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "answer", schema = "public")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String text;

    @Column
    private String nextQuestion;
}
