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
    private String name;
    private String description;
    private DistrictResponseDTO districtResponseDTO;
    private LocationResponseDTO locationResponseDTO;
    private Set<EventCategoryResponseDTO> eventCategoryResponseDTOS;
    private EventStateResponseDTO eventStateResponseDTO;
}
