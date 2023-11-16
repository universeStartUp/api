package com.pe.unieventia.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventNetworkRequestDTO {
    private String TwitterURL;
    private String FacebookURL;
    private String InstagramURL;
}
