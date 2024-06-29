package com.javarush.kotovych.config;


import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ApplicationProperties extends Properties {

    public static final String CONNECTION_URL = "spring.datasource.url";
    public static final String CONNECTION_USERNAME = "spring.datasource.username";
    public static final String CONNECTION_PASSWORD = "spring.datasource.password";



}
