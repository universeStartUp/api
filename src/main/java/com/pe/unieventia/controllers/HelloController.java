package com.pe.unieventia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String saludar(Model model) {
        model.addAttribute("name", "Usuario");
        return "hello";
    }
}