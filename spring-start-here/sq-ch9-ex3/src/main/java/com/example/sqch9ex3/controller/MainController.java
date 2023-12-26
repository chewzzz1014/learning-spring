package com.example.sqch9ex3.controller;

import com.example.sqch9ex3.service.LoggedUserManagementService;
import com.example.sqch9ex3.service.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public MainController(LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/main")
    public String home(@RequestParam(required = false) String logout, Model model) {
        // logout
        if (logout != null) {
            loggedUserManagementService.setUsername(null);
        }

        // login, set username in session scope bean
        String username = loggedUserManagementService.getUsername();
        int count = loginCountService.getCount();
        if (username == null) {
            return "redirect:/";
        }

        model.addAttribute("username", username);
        model.addAttribute("loginCount", count);
        return "main";
    }
}
