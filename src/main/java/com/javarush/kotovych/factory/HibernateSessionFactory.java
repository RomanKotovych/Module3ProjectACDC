package com.javarush.kotovych.factory;

import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

@RequiredArgsConstructor
public class HibernateSessionFactory {
    private final SessionFactory sessionFactory;

    public HibernateSessionFactory() {
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
                .buildSessionFactory();
    }

    public Session createSession() {
        return sessionFactory.openSession();
    }
}
