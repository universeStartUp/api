package com.pe.unieventia.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequestDTO {
    @NotBlank
    @Size(max = 255)
    private String address;
    @NotNull
    private DistrictRequestDTO district;
}
