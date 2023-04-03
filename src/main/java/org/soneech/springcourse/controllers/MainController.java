package org.soneech.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/home")
    public String homePage(
            @RequestParam(required = false, defaultValue = "Dear") String firstName,
            @RequestParam(required = false, defaultValue = "User") String lastName,
            Model model) {

        model.addAttribute("greeting", "Hello, " + firstName + " " + lastName + "!");
        return "main/index";
    }
}
