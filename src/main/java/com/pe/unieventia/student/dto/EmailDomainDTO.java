package com.pe.unieventia.student.dto;

import com.pe.unieventia.student.domain.entity.University;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailDomainDTO {
    @NotBlank(message = "Email domain cannot be empty")
    @Size(min = 3, max = 255, message = "Email domain length must be from 3 to 255 characters")
    private String domain;
    @NotNull(message = "University is required.")
    private University university;
}
    