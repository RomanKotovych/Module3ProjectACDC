package com.javarush.kotovych.repository;

import com.javarush.kotovych.config.SessionCreator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.Optional;

public abstract class BaseRepository<T> implements Repository<T> {

    private final Class<T> entityClass;

    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Collection<T> getAll() {
        try (Session session = SessionCreator.createSession()) {
            Query<T> query = session.createQuery("from %s".formatted(entityClass.getName()), entityClass);
            return query.list();
        }

    }

    @Override
    public Optional<T> get(long id) {
        try (Session session = SessionCreator.createSession()) {
            T entity = session.find(entityClass, id);
            return Optional.ofNullable(entity);
        }
    }

    @Override
    public void create(T entity) {
        try (Session session = SessionCreator.createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = SessionCreator.createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = SessionCreator.createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.remove(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }
}
