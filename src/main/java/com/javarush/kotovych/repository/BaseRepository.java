package com.javarush.kotovych.repository;

import com.javarush.kotovych.config.NanoSpring;
import com.javarush.kotovych.config.SessionCreator;
import com.javarush.kotovych.constants.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public abstract class BaseRepository<T> implements Repository<T> {

    private final SessionCreator sessionCreator = NanoSpring.find(SessionCreator.class);
    private final Class<T> entityClass;

    @Override
    public Collection<T> getAll() {
        Session session = sessionCreator.getSession();
        session.clear();
        Query<T> query = session.createQuery("from %s".formatted(entityClass.getName()), entityClass);
        return query.list();
    }

    @Override
    public Optional<T> get(long id) {
        Session session = sessionCreator.getSession();
//        session.clear();
        Query<T> query = session.createQuery("select e from %s e where id=:id".formatted(entityClass.getName()), entityClass);
        query.setParameter(Constants.ID, id);
//        T entity = session.find(entityClass, id);
//        return Optional.ofNullable(entity);
        return query.stream().findFirst();
    }

    @Override
    public void create(T entity) {
        Session session = sessionCreator.getSession();
        session.clear();
        session.persist(entity);
        session.flush();
    }

    @Override
    public void update(T entity) {
        Session session = sessionCreator.getSession();
        session.clear();
        session.merge(entity);
        session.flush();
    }


    @Override
    public void delete(T entity) {
        Session session = sessionCreator.getSession();
        session.clear();
        session.remove(entity);
        session.flush();
    }

}
