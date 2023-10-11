package com.pe.unieventia.student.dto.input;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UniversityDTO {
    @Size(min = 1, max = 50)
    private String name;
}
