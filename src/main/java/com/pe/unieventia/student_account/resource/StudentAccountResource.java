package com.pe.unieventia.student_account.resource;

import com.pe.unieventia.email.resource.EmailResource;
import com.pe.unieventia.student.resource.StudentResource;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentAccountResource {
    private StudentResource student;
    private EmailResource email;
    @Column(length = 256)
    private String password;
    //private GoogleInfo googleInfo;
}
