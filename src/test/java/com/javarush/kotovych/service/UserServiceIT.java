package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceIT {

    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void givenUser_whenCreateUserCalled_thenUserIsPersisted() {
        User user = new User();
        userService.create(user);
        verify(userRepository, times(1)).create(user);
    }

    @Test
    public void givenUser_whenUpdateUserCalled_thenUserIsUpdated() {
        User user = new User();
        userService.update(user);
        verify(userRepository, times(1)).update(user);
    }

    @Test
    public void givenUser_whenDeleteUserCalled_thenUserIsDeleted() {
        long id = 1L;
        User user = User.builder().id(id).build();
        when(userRepository.get(id)).thenReturn(Optional.of(user));
        userService.delete(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void whenGetAllUsersCalled_thenAllUsersAreReturned() {
        userService.getAll();
        verify(userRepository, times(1)).getAll();
    }

    @Test
    public void givenUserId_whenGetUserCalled_thenUserIsReturned() {
        long id = 1;
        userService.get(id);
        verify(userRepository, times(1)).get(id);
    }

    @Test
    public void givenUserLogin_whenGetUserCalled_thenUserIsReturned() {
        String login = "testUser";
        userService.get(login);
        verify(userRepository, times(1)).get(login);
    }

    @Test
    public void givenUserLoginAndPassword_whenCheckIfCorrectCalled_thenTrueIsReturned() {
        String login = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setPassword(password);
        when(userRepository.get(login)).thenReturn(Optional.of(user));
        boolean result = userService.checkIfCorrect(login, password);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenUserLoginAndPassword_whenCheckIfCorrectCalled_thenFalseIsReturned() {
        String login = "testUser";
        String password = "testPassword";
        when(userRepository.get(login)).thenReturn(Optional.empty());
        boolean result = userService.checkIfCorrect(login, password);
        Assertions.assertFalse(result);
    }

    @Test
    public void givenUserId_whenCheckIfExistsCalled_thenTrueIsReturned() {
        long id = 1;
        when(userRepository.get(id)).thenReturn(Optional.of(new User()));
        boolean result = userService.checkIfExists(id);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenUserId_whenCheckIfExistsCalled_thenFalseIsReturned() {
        long id = 1;
        when(userRepository.get(id)).thenReturn(Optional.empty());
        boolean result = userService.checkIfExists(id);
        Assertions.assertFalse(result);
    }

    @Test
    public void givenUserLogin_whenCheckIfExistsCalled_thenTrueIsReturned() {
        String login = "testUser";
        when(userRepository.get(login)).thenReturn(Optional.of(new User()));
        boolean result = userService.checkIfExists(login);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenUserLogin_whenCheckIfExistsCalled_thenFalseIsReturned() {
        String login = "testUser";
        when(userRepository.get(login)).thenReturn(Optional.empty());
        boolean result = userService.checkIfExists(login);
        Assertions.assertFalse(result);
    }

    @Test
    public void givenUserId_whenDeleteUserCalled_thenUserIsDeleted() {
        long id = 1L;
        User user = User.builder().id(id).build();
        when(userRepository.get(id)).thenReturn(Optional.of(user));
        userService.delete(id);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void givenUserId_whenDeleteUserCalled_thenNothingHappens() {
        long id = 1;
        when(userRepository.get(id)).thenReturn(Optional.empty());
        userService.delete(id);
        verify(userRepository, times(0)).delete(any());
    }

    @Test
    public void givenUserId_whenGetUserIfExistsCalled_thenUserIsReturned() {
        long id = 1;
        User user = new User();
        when(userRepository.get(id)).thenReturn(Optional.of(user));
        User result = userService.getIfExists(id);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void givenUserId_whenGetUserIfExistsCalled_thenNullIsReturned() {
        long id = 1;
        when(userRepository.get(id)).thenReturn(Optional.empty());
        User result = userService.getIfExists(id);
        Assertions.assertNull(result);
    }

    @Test
    public void givenUserLogin_whenGetUserIfExistsCalled_thenUserIsReturned() {
        String login = "testUser";
        User user = new User();
        when(userRepository.get(login)).thenReturn(Optional.of(user));
        User result = userService.getIfExists(login);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void givenUserLogin_whenGetUserIfExistsCalled_thenNullIsReturned() {
        String login = "testUser";
        when(userRepository.get(login)).thenReturn(Optional.empty());
        User result = userService.getIfExists(login);
        Assertions.assertNull(result);
    }
}