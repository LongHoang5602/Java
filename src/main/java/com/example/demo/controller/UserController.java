package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;

import jakarta.validation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    // @PostMapping("/login")
    // public String processLogin(@RequestParam String username, @RequestParam
    // String password, Model model) {
    // User user = userService.findByUsername(username);
    // if (user == null || !user.getPassword().equals(password)) {
    // model.addAttribute("error", "Invalid username or password");
    // return "login";
    // } else {
    // // Đăng nhập thành công, thực hiện các xử lý khác tại đây
    // return "redirect:/home";
    // }
    // }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(error -> model.addAttribute(error.getField() + " _error", error.getDefaultMessage()));
            return "user/register";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println(user);
        userService.save(user);
        System.out.println(user);
        return "redirect:/login";
    }
}
