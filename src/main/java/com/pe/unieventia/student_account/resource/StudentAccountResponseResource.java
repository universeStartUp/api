package com.pe.unieventia.student_account.resource;

import com.pe.unieventia.email.resource.EmailResponseResource;
import com.pe.unieventia.student.resource.StudentResponseResource;

import lombok.Data;

@Data
public class StudentAccountResponseResource {
    private Long studentAccountId;
    private StudentResponseResource student;
    private EmailResponseResource email;
    private String password;
}
