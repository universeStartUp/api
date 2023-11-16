package com.pe.unieventia.notificationConfig.controller;

import com.pe.unieventia.notificationConfig.dto.NotificationSettingsDto;
import com.pe.unieventia.notificationConfig.model.NotificationConfig;
import com.pe.unieventia.notificationConfig.services.NotificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationSettingsController {
    private final NotificationConfigService configService;
    @Autowired
    public NotificationSettingsController(NotificationConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/settings")
    public String showNotificationSettingsForm(Model model) {
        model.addAttribute("settings", new NotificationSettingsDto());
        return "settings-form";
    }

    @PostMapping("/settings")
    public String handleNotificationSettingsSubmission(NotificationSettingsDto settingsDto) {
        NotificationConfig config = new NotificationConfig();
        config.setEmailEnabled(settingsDto.isEmailEnabled());
        config.setSmsEnabled(settingsDto.isSmsEnabled());
        config.setReceiveNewsletter(settingsDto.isReceivedNewsletter());
        config.setLanguagePreference(settingsDto.getLanguagePreference());
        System.out.println((config.getEmailEnabled()));
        System.out.println(config.getLanguagePreference());

        configService.saveNotificationConfig(config);

        return "settings-confirmation";
    }

}
