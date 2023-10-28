package com.pe.unieventia.student_account.dto;

import java.time.LocalDateTime;

import com.pe.unieventia.student.dto.StudentResponseDTO;

import lombok.Data;

@Data
public class StudentAccountResponseDTO {
    private Long studentAccountId;
    private StudentResponseDTO student;
    private LocalDateTime creationDateTime;
}
