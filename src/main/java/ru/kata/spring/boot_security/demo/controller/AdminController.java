package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class  AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public void saveUser(User user) {
        userService.add(user);
    }

    @PutMapping ("/updateUser")
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(User user) {
        userService.removeUserById(user.getId());
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> showUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<User>> getUser(HttpSession session) {
        List<User> userList = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        userList.add(user);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}