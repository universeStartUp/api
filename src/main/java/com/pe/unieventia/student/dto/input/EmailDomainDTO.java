package com.pe.unieventia.student.dto.input;

import com.pe.unieventia.student.domain.entity.University;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailDomainDTO {
    @NotNull
    @Size(min = 1, max = 255)
    private String domain;

    @NotNull
    private University university;
}
    