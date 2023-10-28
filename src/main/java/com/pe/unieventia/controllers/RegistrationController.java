package com.pe.unieventia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration-for-event")
    public String showForm() {
        return "registration-for-event";
    }
}