package org.katrin.onlineshop.controller;

import lombok.AllArgsConstructor;
import org.katrin.onlineshop.model.UserEntity;
import org.katrin.onlineshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor

@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registration")
    public String create(UserEntity userEntity, Model model) {
        if (!userService.create(userEntity)) {
            model.addAttribute("errorMessage", "User with email " + userEntity.getEmail() + " already exists");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/users/{user}")
    public String userInfo(@PathVariable UserEntity user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }
}
