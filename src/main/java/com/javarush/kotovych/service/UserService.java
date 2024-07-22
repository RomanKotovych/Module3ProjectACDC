package com.javarush.kotovych.service;

import com.javarush.kotovych.dto.UserTo;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.mapping.Dto;
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
public class UserService {
    private final UserRepository userRepository;

    public List<UserTo> getAll() {
        return userRepository.findAll()
                .stream()
                .map(Dto.MAPPER::toDto)
                .toList();
    }

    public UserTo get(long id) {
        return Dto.MAPPER.toDto(userRepository.findById(id).orElse(null));
    }

    public UserTo get(String name){
        return Dto.MAPPER.toDto(userRepository.findByUsername(name));
    }

    public void create(UserTo entity) {
        userRepository.save(Dto.MAPPER.toEntity(entity));
    }

    public void update(UserTo entity) {
        userRepository.save(Dto.MAPPER.toEntity(entity));
    }

    public void delete(UserTo entity) {
        userRepository.delete(Dto.MAPPER.toEntity(entity));
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