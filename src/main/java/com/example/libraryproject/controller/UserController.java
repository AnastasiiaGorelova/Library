package com.example.libraryproject.controller;

import com.example.libraryproject.model.User;
import com.example.libraryproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    private String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    //особенность контроллера: отдельная страница для отображения формы, в которую вбиваем данные,
    // отдельная - для непосредственного создания юзера
    @GetMapping("/user-create")
    private String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    private String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    private String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    private String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    private String updateUser(User user) {
        userService.saveUser(user); //сам определяет, изменили мы старого или добавили нового
        return "redirect:/users";
    }

}