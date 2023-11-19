package com.pe.unieventia.config.api;

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
        config.setEmailEnabled(true); // ajusta según tu entidad Config
        config.setSmsEnabled(false); // ajusta según tu entidad Config
        config.setReceiveNewsletter(false);
        config.setLanguagePreference("es");
        // Configura el resto de las propiedades de Config según sea necesario

        when(configService.getNotificationConfig(anyLong())).thenReturn(Optional.of(config));

        ResponseEntity<ConfigDto> response = configController.getConfig(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Aquí deberías verificar también los campos específicos del DTO, como se hace en tus pruebas de evento
    }

    // Agrega más pruebas para otros métodos como POST, etc.
}

