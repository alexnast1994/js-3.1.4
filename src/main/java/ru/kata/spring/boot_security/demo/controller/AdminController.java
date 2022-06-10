package ru.kata.spring.boot_security.demo.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@ComponentScan("/src/main/resources/templates")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User user) {
        userService.add(user);
    }

    @PutMapping ("/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
    }

    @GetMapping(value = "/main")
    public ResponseEntity<List<User>> showUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }



    @GetMapping("/getUser")
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}