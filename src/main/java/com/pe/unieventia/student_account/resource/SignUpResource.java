package com.pe.unieventia.student_account.resource;

import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.resource.StudentResource;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignUpResource {
    StudentResource student;
    @Email
    String email;
    @Column(length = 128)
    String password;
}
