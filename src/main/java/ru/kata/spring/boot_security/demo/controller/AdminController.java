package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/home")
    public String startPage(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";

    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "user-create";

    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        if (isNull(user.getId())) {
            userService.add(user);
        }
        return "redirect:/admin/home";
    }

    @GetMapping(value = "/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        userService.dropPassword(user);
        model.addAttribute("user", user);
        return "user-update";

    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin/home";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user) {
        if (nonNull(user.getId())) {
            userService.updateUser(user);
        }
        return "redirect:/admin/home";
    }
}