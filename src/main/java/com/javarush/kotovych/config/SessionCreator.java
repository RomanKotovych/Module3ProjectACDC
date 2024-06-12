package com.javarush.kotovych.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SessionCreator {
    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<AtomicInteger> levelBox = new ThreadLocal<>();
    private static final ThreadLocal<Session> sessionBox = new ThreadLocal<>();


    static {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public static Session createSession() {
        return sessionBox.get() == null || !sessionBox.get().isOpen()
                ? sessionFactory.openSession()
                : sessionBox.get();
    }

    public static void beginTransactional() {
        if (levelBox.get () == null) {
            levelBox.set(new AtomicInteger(0));
        }
        AtomicInteger level = levelBox.get ();
        if (level.getAndIncrement() == 0) {
            Session session = createSession();
            sessionBox.set(session);
            session.beginTransaction();
            log.info("begin level: {}", level.get());
        }
    }

    public static void endTransactional() {
        AtomicInteger level = levelBox.get ();
        Session session = sessionBox.get ();
        log.info("end level: {}", level.get());
        if (level.decrementAndGet () == 0) {
            try {
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }


    @PreDestroy
    public void close() {
        sessionFactory.close();
    }
}
