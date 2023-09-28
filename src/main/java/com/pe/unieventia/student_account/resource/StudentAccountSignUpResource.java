package com.pe.unieventia.student_account.resource;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class StudentAccountSignUpResource {
    String username;
    String firstname;
    String lastname;
    @Email
    String email;
    String password;
}
