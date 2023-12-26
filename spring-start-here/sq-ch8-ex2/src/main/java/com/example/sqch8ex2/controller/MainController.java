package com.example.sqch8ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class MainController {

    @GetMapping("/{color}")
    public String home(@PathVariable String color, Model page) {
        page.addAttribute("username","chewzzz");
        page.addAttribute("color", color);
        return "home";
    }
}
