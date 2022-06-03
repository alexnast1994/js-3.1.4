package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    User getUserByUserName(String userName);

    void dropPassword(User user);

}
