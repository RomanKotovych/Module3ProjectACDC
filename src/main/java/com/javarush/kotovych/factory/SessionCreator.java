package com.javarush.kotovych.factory;

import jakarta.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreator {
    private static final SessionFactory sessionFactory;


    static {

        sessionFactory = new Configuration()
                .configure()
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
