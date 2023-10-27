package com.pe.unieventia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponseDTO {
    private Long id;
    private String name;
    private DepartmentResponseDTO department;
}
