package com.javarush.kotovych.repository;

import com.javarush.kotovych.entity.User;

public class UserRepository extends BaseRepository<User> {
    public UserRepository(Class<User> entityClass) {
        super(entityClass);
    }
}
