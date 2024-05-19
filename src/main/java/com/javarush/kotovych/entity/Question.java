package com.javarush.kotovych.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.boot.model.source.spi.HibernateTypeSource;
import org.hibernate.type.SerializableToBlobType;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String situation;
    private String text;

    private Map<String, String> answers;
}
