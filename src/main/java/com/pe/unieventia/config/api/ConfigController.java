package com.pe.unieventia.config.api;

import com.pe.unieventia.config.dto.ConfigDto;
import com.pe.unieventia.config.domain.entity.Config;
import com.pe.unieventia.config.domain.service.ConfigService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigService configService;

    @GetMapping("/{id}")
    public ResponseEntity<ConfigDto> getConfig(@PathVariable Long id) {
        Optional<Config> configOpt = configService.getNotificationConfig(id);

        if (configOpt.isPresent()) {
            ConfigDto configDto = convertConfigToDto(configOpt.get());
            return ResponseEntity.ok(configDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Config> updateConfig(@RequestBody ConfigDto configDto) {
        // Aquí debes decidir cómo manejar la actualización, por ejemplo, si es una actualización o una creación nueva
        // Suponiendo que es una actualización, necesitarás un ID para identificar qué configuración actualizar
        Config config = convertDtoToConfig(configDto);
        Config updatedConfig = configService.saveNotificationConfig(config);
        return ResponseEntity.ok(updatedConfig);
    }

    // Métodos de conversión (estos deben ser implementados)
    private ConfigDto convertConfigToDto(Config config) {
        // Implementar lógica de conversión
        return new ConfigDto();
    }

    private Config convertDtoToConfig(ConfigDto configDto) {
        // Implementar lógica de conversión
        return new Config();
    }
}
