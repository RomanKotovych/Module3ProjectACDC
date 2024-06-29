package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.repository.Repository;
import com.javarush.kotovych.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService implements Repository<User> {
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(long id) {
        return userRepository.getReferenceById(id);
    }

    public User get(String name){
        return userRepository.findByUsername(name);
    }

    @Override
    public void create(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void update(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public boolean existsById(long id){
        return userRepository.existsById(id);
    }

    public boolean checkIfCorrect(String username, String password){
        return userRepository.exists(username, password);
    }

}