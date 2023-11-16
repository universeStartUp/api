package com.pe.unieventia.notificationConfig.dto;

import lombok.Getter;
import lombok.Setter;

public class NotificationSettingsDto {
    @Getter @Setter
    private boolean emailEnabled;
    @Getter @Setter
    private boolean smsEnabled;
    @Getter @Setter
    private boolean receivedNewsletter;
    @Getter @Setter
    private String languagePreference;

}
