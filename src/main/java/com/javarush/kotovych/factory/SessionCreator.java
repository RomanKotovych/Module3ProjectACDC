package com.javarush.kotovych.factory;

import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.entity.Question;
import com.javarush.kotovych.entity.User;
import jakarta.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class SessionCreator{
    private static final SessionFactory sessionFactory;


    static  {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/game");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "postgres");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Quest.class)
                .addAnnotatedClass(Question.class)
                .buildSessionFactory();
    }

    public static Session createSession() {
        return sessionFactory.openSession();
    }


    @PreDestroy
    public void close() {
        sessionFactory.close();
    }
}
