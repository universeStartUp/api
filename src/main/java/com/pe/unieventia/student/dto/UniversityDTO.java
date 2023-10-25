package com.pe.unieventia.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UniversityDTO {
    @NotBlank(message = "University name cannot be empty")
    @Size(max = 50, message = "University name length cannot be longer than 50 characters")
    private String name;
}
