package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class LoginController {

    @GetMapping(value = "login")
    public String getLoginPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/login";
    }

    @GetMapping("/")
    public String redirect() {
        return "login";
    }
}