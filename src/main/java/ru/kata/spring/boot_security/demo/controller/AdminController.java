package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
public class AdminController {

    private final UserService userservice;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userservice, RoleService roleService) {
        this.userservice = userservice;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userservice.getAllUsers());
        model.addAttribute("user", userservice.loadUserByUsername(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String getRegistrationForm(Model model, Principal principal) {

        User user = new User();
        model.addAttribute("user", user);

        return "new";
    }

    @PostMapping("/admin/new")
    public String registerNewUser(@ModelAttribute("user") User user) {
        userservice.addUser(user);
        return "redirect:/admin";
    }


    @PutMapping("/admin/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userservice.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userservice.deleteUser(id);
        return "redirect:/admin";
    }
}
