package com.pe.unieventia.event.dto;

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
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private DateRequestDTO date;
    @NotNull
    private LocationRequestDTO location;
    @NotNull
    private Set<EventCategoryRequestDTO> eventCategories;
    @NotNull
    private EventStateRequestDTO eventState;
    @NotNull
    private EventNetworkRequestDTO eventNetwork;
}
