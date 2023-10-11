package com.pe.unieventia.student_account.dto;

import com.pe.unieventia.student.dto.input.StudentDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDTO {
    @NotNull
    StudentDTO student;
    
    @Email
    @NotNull
    String email;

    @NotNull
    @Size(min = 5, max = 128, message = "Password length must be from 5 to 128 characters")
    String password;
}
