package com.pe.unieventia.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseDTO {
    private Long id;
    private String address;
    private DistrictResponseDTO district;
}
