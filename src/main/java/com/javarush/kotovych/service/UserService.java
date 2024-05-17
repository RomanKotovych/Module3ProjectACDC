package com.javarush.kotovych.service;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.factory.SessionCreator;
import com.javarush.kotovych.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService extends UserRepository {


    public UserService() {
        super(User.class);
    }

    public boolean checkIfExists(long id) {
        User user = get(id).orElse(null);
        return user != null;
    }


    public User getIfExists(long id) {
        return get(id).orElse(new User());
    }

    public User getIfExists(String username) {
        try (Session session = SessionCreator.createSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter(Constants.USERNAME, username);

            return query.uniqueResult();
        }
    }

    public boolean checkIfCorrect(String username, String password) {
        try (Session session = SessionCreator.createSession()) {
            Query<User> query = session.createQuery("from User where username = :username and password = :password", User.class);
            query.setParameter(Constants.USERNAME, username);
            query.setParameter(Constants.PASSWORD, password);

            User user = query.uniqueResult();
            return user != null;
        }
    }
}