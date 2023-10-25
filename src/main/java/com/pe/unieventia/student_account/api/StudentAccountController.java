package com.pe.unieventia.student_account.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.unieventia.student_account.domain.service.StudentAccountService;
import com.pe.unieventia.student_account.dto.SignUpDTO;
import com.pe.unieventia.student_account.dto.StudentAccountResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class StudentAccountController {
    private final StudentAccountService studentAccountService;

    @PostMapping
    private ResponseEntity<StudentAccountResponseDTO> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        StudentAccountResponseDTO studentAccountResponseResource = studentAccountService.createStudentAccount(
            signUpDTO.getSurname(),
            signUpDTO.getFirstName(),
            signUpDTO.getLastName(),
            signUpDTO.getStudentCode(),
            signUpDTO.getPhoneNumber(),
            signUpDTO.getEmailAddress(),
            signUpDTO.getPassword()
        );

        return new ResponseEntity<>(studentAccountResponseResource, HttpStatus.CREATED);
    }

}
