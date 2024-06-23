package com.javarush.kotovych.config;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Getter
public class ApplicationContextHolder {
    private static final ApplicationContext applicationContext;

    static {
        applicationContext = new AnnotationConfigApplicationContext("com.javarush.kotovych");
    }
}
