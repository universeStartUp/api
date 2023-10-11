package com.pe.unieventia.student.dto.response;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String studentCode;
}
