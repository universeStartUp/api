package com.pe.unieventia.student.dto;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private Long studentId;
    private String surname;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String studentCode;
    private EmailResponseDTO email;
}
