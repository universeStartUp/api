package com.pe.unieventia.config.domain.service;

import com.pe.unieventia.config.domain.entity.Config;
import com.pe.unieventia.config.domain.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigService {
    private final ConfigRepository repository;

    @Autowired
    public ConfigService(ConfigRepository repository) {
        this.repository = repository;
    }

    public Config saveNotificationConfig(Config config) {
        return repository.save(config);
    }

    public Optional<Config> getNotificationConfig(Long id) {
        // Utiliza findById para obtener la configuración por su ID.
        return repository.findById(id);
    }
}
