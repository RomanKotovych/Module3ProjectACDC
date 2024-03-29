package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.exception.AppException;
import com.javarush.kotovych.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(User user) {
        if (checkIfExists(user.getId())) {
            userRepository.delete(user);
        }
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long id) {
        return userRepository.get(id);
    }

    public Optional<User> get(String login) {
        return userRepository.get(login);
    }

    public boolean checkIfCorrect(String login, String password) {
        User user = getIfExists(login);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public boolean checkIfExists(long id) {
        Optional<User> userOptional = userRepository.get(id);
        return userOptional.isPresent();
    }

    public boolean checkIfExists(String username) {
        Optional<User> userOptional = userRepository.get(username);
        return userOptional.isPresent();
    }

    public void delete(long id) {
        User user = getIfExists(id);
        if (user != null) {
            delete(user);
        }
    }

    public User getIfExists(long id) {
        Optional<User> userOptional = get(id);
        return userOptional.orElse(null);
    }

    public User getIfExists(String username) {
        Optional<User> userOptional = get(username);
        return userOptional.orElse(null);
    }
}