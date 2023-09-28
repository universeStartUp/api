package com.pe.unieventia.student.resource;

import lombok.Data;

@Data
public class StudentResponseResource {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String studentCode;
}
