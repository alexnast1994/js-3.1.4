//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.kata.spring.boot_security.demo.models.User;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @GetMapping
//    public ResponseEntity<List<User>> getUserName(HttpSession session) {
//        List<User> userList = new ArrayList<>();
//        User user = (User) session.getAttribute("user");
//        userList.add(user);
//        return new ResponseEntity<>(userList, HttpStatus.OK);
//    }
//}
