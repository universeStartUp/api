package com.pe.unieventia.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventNetworkResponseDTO {
    private Long id;
    private String TwitterURL;
    private String FacebookURL;
    private String InstagramURL;
}
