package com.pe.unieventia.student_account.dto;

import com.pe.unieventia.student.dto.response.StudentResponseDTO;

import lombok.Data;

@Data
public class SignUpResponseDTO {
    private Long studentAccountId;
    private StudentResponseDTO student;
    private String email;
    
}
