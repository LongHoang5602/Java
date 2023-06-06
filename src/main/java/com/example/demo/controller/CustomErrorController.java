package com.example.demo.controller;

import java.util.Optional;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    @GetMapping
    public String handleError(HttpServletRequest request) {
        Optional<Object> status = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return status.filter(s -> Integer.parseInt(s.toString()) == 404)
                .map(s -> "error/404")
                .orElse(null);
    }

}
// @GetMapping
// public String handleError(HttpServletRequest request) {
// return
// Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
// .filter(status -> Integer.parseInt(status.toString()) == 404)
// .map(status -> "error/404")
// .orElse(null));
// }
