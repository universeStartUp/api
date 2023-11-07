package com.pe.unieventia.user.dto;

import java.time.LocalDateTime;

import com.pe.unieventia.student.dto.StudentResponseDTO;

import lombok.Data;

@Data
public class StudentAccountResponseDTO {
    private Long studentAccountId;
    private StudentResponseDTO student;
    private LocalDateTime creationDateTime;
}
