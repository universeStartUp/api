package com.pe.unieventia.student_account.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.unieventia.student_account.domain.service.StudentAccountService;
import com.pe.unieventia.student_account.dto.SignUpDTO;
import com.pe.unieventia.student_account.dto.SignUpResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class StudentAccountController {
    private final StudentAccountService studentAccountService;

    @PostMapping
    private ResponseEntity<SignUpResponseDTO> createStudentAccount(@Valid @RequestBody SignUpDTO studentAccountSignUpResource) {
        SignUpResponseDTO studentAccountResponseResource = studentAccountService.createStudentAccountResponse(studentAccountSignUpResource);
        return new ResponseEntity<>(studentAccountResponseResource, HttpStatus.CREATED);
    }
}
