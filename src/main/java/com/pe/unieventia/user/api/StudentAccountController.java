package com.pe.unieventia.user.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.unieventia.user.domain.service.StudentAccountService;
import com.pe.unieventia.user.dto.SignUpRequestDTO;
import com.pe.unieventia.user.dto.StudentAccountResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class StudentAccountController {
    private final StudentAccountService studentAccountService;

    @PostMapping
    private ResponseEntity<StudentAccountResponseDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        StudentAccountResponseDTO studentAccountResponseResource = studentAccountService.createStudentAccount(
            signUpRequestDTO.getSurname(),
            signUpRequestDTO.getFirstName(),
            signUpRequestDTO.getLastName(),
            signUpRequestDTO.getStudentCode(),
            signUpRequestDTO.getPhoneNumber(),
            signUpRequestDTO.getEmailAddress(),
            signUpRequestDTO.getPassword()
        );

        return new ResponseEntity<>(studentAccountResponseResource, HttpStatus.CREATED);
    }

}
