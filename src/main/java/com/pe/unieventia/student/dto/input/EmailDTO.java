package com.pe.unieventia.student.dto.input;

import com.pe.unieventia.student.domain.entity.EmailDomain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDTO {
    @NotNull
    @Column(length = 64)
    private String local;

    @NotNull
    private EmailDomain emailDomain;
}
