package com.pe.unieventia.dto;

import lombok.Getter;

public class NotificationSettingsDto {
    @Getter
    private boolean emailEnabled = false;
    @Getter
    private boolean smsEnabled = false;
    private boolean receivedNewsletter = false;
    private String languagePreference = "es";


    public boolean isReceiveNewsletter() {
        return receivedNewsletter;
    }

    public String getLanguagePreference() {
        return languagePreference;
    }
}
