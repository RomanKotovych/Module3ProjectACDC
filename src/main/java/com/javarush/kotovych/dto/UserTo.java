package com.javarush.kotovych.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserTo implements Serializable {
    private long id;
    private String username;
    private String password;
    private int wins;
    private int losses;
}