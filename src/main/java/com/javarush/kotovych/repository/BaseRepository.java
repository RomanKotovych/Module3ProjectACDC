package com.javarush.kotovych.repository;

import com.javarush.kotovych.factory.HibernateSessionFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<T> implements Repository<T> {

    private final Class<T> entityClass;
    protected final HibernateSessionFactory sessionFactory;

    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        sessionFactory = new HibernateSessionFactory();
    }

    @Override
    public Collection<T> getAll() {
        try (Session session = sessionFactory.createSession()) {
            Query<T> query = session.createQuery("from %s".formatted(entityClass.getName()), entityClass);
            return query.list();
        }
    }

    @Override
    public Optional<T> get(long id) {
        try (Session session = sessionFactory.createSession()) {
            T entity = session.find(entityClass, id);
            return Optional.ofNullable(entity);
        }
    }

    @Override
    public void create(T entity) {
        try (Session session = sessionFactory.createSession()) {
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
        try (Session session = sessionFactory.createSession()) {
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
        try (Session session = sessionFactory.createSession()) {
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
