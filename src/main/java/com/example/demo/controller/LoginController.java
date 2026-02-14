package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
    	System.out.println("Entrando al método login() del LoginController");
        return "login"; 
    }
}

