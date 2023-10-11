package com.pe.unieventia.student.dto.input;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentAccountDTO {
    private StudentDTO student;
    private EmailDTO email;
    @Column(length = 256)
    private String password;    
}
