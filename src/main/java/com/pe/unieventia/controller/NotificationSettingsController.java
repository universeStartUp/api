package com.pe.unieventia.controller;

import com.pe.unieventia.dto.NotificationSettingsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationSettingsController {
    @GetMapping("/settings")
    public String showNotificationSettingsForm(Model model) {
        model.addAttribute("settings", new NotificationSettingsDto());
        return "settings-form";
    }

    @PostMapping("/settings")
    public String handleNotificationSettingsSubmission(NotificationSettingsDto settings) {
        System.out.println("Email Enabled: " + settings.isEmailEnabled());
        System.out.println("SMS Enabled: " + settings.isSmsEnabled());
        System.out.println("Receive Newsletter: " + settings.isReceivedNewsletter());
        System.out.println("Language Preference: " + settings.getLanguagePreference());

        return "settings-confirmation";
    }

}
