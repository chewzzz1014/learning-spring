package com.example.sqch9ex2.controller;

import com.example.sqch9ex2.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;

    public MainController(LoggedUserManagementService loggedUserManagementService) {
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/main")
    public String home(@RequestParam(required = false) String logout, Model model) {
        // logout
        if (logout != null) {
            loggedUserManagementService.setUsername(null);
        }

        // login, set username in session scope bean
        String username = loggedUserManagementService.getUsername();
        if (username == null) {
            return "redirect:/";
        }

        return "main";
    }
}
