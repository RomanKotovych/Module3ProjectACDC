package com.javarush.kotovych.repository;

import com.javarush.kotovych.config.SessionCreator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public abstract class BaseRepository<T> implements Repository<T> {

    private final Class<T> entityClass;

    @Override
    public Collection<T> getAll() {
        Session session = SessionCreator.getSession();
        Query<T> query = session.createQuery("from %s".formatted(entityClass.getName()), entityClass);
        return query.list();
    }

    @Override
    public Optional<T> get(long id) {
        Session session = SessionCreator.getSession();
        T entity = session.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public void create(T entity) {
        Session session = SessionCreator.getSession();
        session.persist(entity);
    }

    @Override
    public void update(T entity) {
        Session session = SessionCreator.getSession();
        session.merge(entity);
    }


    @Override
    public void delete(T entity) {
        Session session = SessionCreator.getSession();
        session.remove(entity);
    }

}
