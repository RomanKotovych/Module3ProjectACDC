package com.javarush.kotovych.service;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.config.SessionCreator;
import com.javarush.kotovych.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@Transactional
public class UserService extends UserRepository {

    public UserService() {
        super(User.class);
    }


    public boolean checkIfExists(long id) {
        User user = get(id).orElse(null);
        return user != null;
    }


    public User getIfExists(long id) {
        return get(id).orElse(null);
    }

    public User getIfExists(String username) {
        Session session = SessionCreator.getSession();
        Query<Long> query = session.createQuery("select u.id from User u where u.username = :username", Long.class);
        query.setParameter(Constants.USERNAME, username);

        Long userId = query.uniqueResult();
        if(userId == null){
            return null;
        }
        return getIfExists(userId);
    }

    public boolean checkIfCorrect(String username, String password) {
        Session session = SessionCreator.getSession();
        Query<User> query = session.createQuery("from User where username = :username and password = :password", User.class);
        query.setParameter(Constants.USERNAME, username);
        query.setParameter(Constants.PASSWORD, password);

        User user = query.uniqueResult();
        return user != null;
    }
}