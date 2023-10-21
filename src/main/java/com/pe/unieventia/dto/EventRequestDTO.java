package com.pe.unieventia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private DateRequestDTO dateRequestDTO;
    @NotNull
    private LocationRequestDTO locationRequestDTO;
    @NotNull
    private Set<EventCategoryRequestDTO> eventCategoryRequestDTOS;
    @NotNull
    private EventStateRequestDTO eventStateRequestDTO;
}
