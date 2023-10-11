package com.pe.unieventia.student.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailStrDTO {
    @Email
    @NotNull
    private String email;
}
