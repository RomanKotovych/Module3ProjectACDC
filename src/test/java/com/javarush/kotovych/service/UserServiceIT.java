package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class UserServiceIT {

    UserService userService;

    @BeforeEach
    void setUp(){
        UserRepository userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void create() {
        User user = new User("testLogin", "testPassword");
        userService.create(user);
        verify(userService, times(1)).create(user);
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void checkIfCorrect() {
    }

    @Test
    void checkIfExists() {
    }

    @Test
    void testCheckIfExists() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void getIfExists() {
    }

    @Test
    void testGetIfExists() {
    }
}