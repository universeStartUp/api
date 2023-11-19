package com.pe.unieventia.controller;

import com.pe.unieventia.config.api.ConfigController;
import com.pe.unieventia.config.domain.entity.Config;
import com.pe.unieventia.config.domain.service.ConfigService;
import com.pe.unieventia.config.dto.ConfigDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ConfigControllerTest {
    @InjectMocks
    private ConfigController configController;

    @Mock
    private ConfigService configService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetConfig() {
        Long id = 1L;
        Config config = new Config();
        config.setEmailEnabled(true);
        config.setSmsEnabled(false);
        config.setReceiveNewsletter(false);
        config.setLanguagePreference("es");

        when(configService.getNotificationConfig(anyLong())).thenReturn(Optional.of(config));

        ResponseEntity<ConfigDto> response = configController.getConfig(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // verificar también los campos específicos del DTO
    }

    @Test
    public void testUpdateConfig() {
        ConfigDto configDto = new ConfigDto();
        // Configura ConfigDto
        configDto.setEmailEnabled(true);
        configDto.setSmsEnabled(false);
        configDto.setReceivedNewsletter(false);
        configDto.setLanguagePreference("es");

        Config config = new Config();
        // Simula la conversión del DTO a la entidad Config
        config.setEmailEnabled(configDto.isEmailEnabled());
        config.setSmsEnabled(configDto.isSmsEnabled());
        config.setReceiveNewsletter(configDto.isReceivedNewsletter());
        config.setLanguagePreference(configDto.getLanguagePreference());

        // Simula la respuesta esperada del servicio
        when(configService.saveNotificationConfig(any(Config.class))).thenReturn(config);

        ResponseEntity<Config> response = configController.updateConfig(configDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(config, response.getBody());
    }

}