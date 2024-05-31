package com.javarush.kotovych.entity;

import com.javarush.kotovych.factory.SessionCreator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_id")
    private List<Question> questions = new ArrayList<>();

    @Column
    private String author;
}
