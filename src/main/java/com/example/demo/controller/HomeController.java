package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpServletResponse response)
	{
		 
		return "index";
	}

	
}
