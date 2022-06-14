package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService serviceUser;

    public UserController(UserService serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping("/main")
    public String getTable(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "main";
    }
}
