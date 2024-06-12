package com.javarush.kotovych.repository;


import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {

    Collection<T> getAll();

    Optional<T> get(long id);


    void create(T entity);

    void update(T entity);

    void delete(T entity);

    T findByParameter(String parameterName, String value);
}
