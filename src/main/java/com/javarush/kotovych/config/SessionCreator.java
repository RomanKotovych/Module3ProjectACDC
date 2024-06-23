package com.javarush.kotovych.config;

import com.javarush.kotovych.entity.Answer;
import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.entity.Question;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.exception.AppException;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class SessionCreator {
    private final SessionFactory sessionFactory;
    private final ThreadLocal<AtomicInteger> levelBox = new ThreadLocal<>();
    private final ThreadLocal<Session> sessionBox = new ThreadLocal<>();

    @Autowired
    public SessionCreator(ApplicationProperties applicationProperties, LiquibaseInit liquibaseInit) {
        liquibaseInit.init();
        sessionFactory = new Configuration()
                .addProperties(applicationProperties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Quest.class)
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Answer.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionBox.get() == null || !sessionBox.get().isOpen()
                ? sessionFactory.openSession()
                : sessionBox.get();
    }

    public void beginTransactional() {
        if (levelBox.get() == null) {
            levelBox.set(new AtomicInteger(0));
        }
        AtomicInteger level = levelBox.get();
        if (level.getAndIncrement() == 0) {
            Session session = getSession();
            sessionBox.set(session);
            session.beginTransaction();
        }
    }

    public void endTransactional() {
        AtomicInteger level = levelBox.get();
        Session session = sessionBox.get();
        if (level.decrementAndGet() == 0) {
            sessionBox.remove();
            try {
                session.getTransaction().commit();
                session.close();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                session.close();
                throw new AppException(e);
            }
        }
    }


    @PreDestroy
    public void close() {
        sessionFactory.close();
    }
}
