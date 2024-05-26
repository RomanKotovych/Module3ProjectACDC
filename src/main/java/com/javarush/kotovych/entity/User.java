package com.javarush.kotovych.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private int wins;

    @Column
    private int losses;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
