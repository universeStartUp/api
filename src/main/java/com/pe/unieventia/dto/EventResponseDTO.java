package com.pe.unieventia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private Long id;
    private String title;
    private String description;
    private DateResponseDTO date;
    private LocationResponseDTO location;
    private Set<EventCategoryResponseDTO> eventCategories;
    private EventStateResponseDTO eventState;
}
