package com.javarush.kotovych.repository;


import com.javarush.kotovych.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

    @Query("select (count(u) > 0) from User u where u.username = ?1 and u.password = ?2")
    boolean exists(String username, String password);
}
