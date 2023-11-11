package com.pe.unieventia.notificationConfig.services;

import com.pe.unieventia.notificationConfig.model.NotificationConfig;
import com.pe.unieventia.notificationConfig.repository.NotificationConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConfigService {

    private final NotificationConfigRepository repository;

    @Autowired
    public NotificationConfigService(NotificationConfigRepository repository) {
        this.repository = repository;
    }

    public NotificationConfig saveNotificationConfig(NotificationConfig config) {
        return repository.save(config);
    }
}