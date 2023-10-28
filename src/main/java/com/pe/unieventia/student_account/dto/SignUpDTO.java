package com.pe.unieventia.student_account.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDTO {
    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 20, message = "Surname length cannot be longer than 20 characters")
    private String surname;
    @NotBlank(message = "First name cannot be empty")
    @Size(max = 50, message = "First name length cannot be longer than 50 characters")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name length cannot be longer than 50 characters")
    private String lastName;
    @NotBlank(message = "Student code cannot be empty")
    @Size(max = 15, message = "Student code length cannot be longer than 15 characters")
    private String studentCode;
    @NotBlank(message = "Phone number cannot be empty")
    @Size(max = 30, message = "Phone number length cannot be longer than 30 characters")
    private String phoneNumber;
    @NotBlank(message = "Email cannot be empty")
    @Email
    private String emailAddress;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 128, message = "Password length must be from 5 to 128 characters")
    private String password;
}
