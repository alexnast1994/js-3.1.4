package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    User getUserByUserName(String userName);


    public void encodePassword(User user);
}
