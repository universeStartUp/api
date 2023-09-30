package com.pe.unieventia.student_account.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.unieventia.student_account.domain.service.StudentAccountService;
import com.pe.unieventia.student_account.resource.StudentAccountResponseResource;
import com.pe.unieventia.student_account.resource.SignUpResource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class StudentAccountController {
    private final StudentAccountService studentAccountService;

    @PostMapping
    private StudentAccountResponseResource createStudentAccount(@Valid @RequestBody SignUpResource studentAccountSignUpResource) {
        StudentAccountResponseResource studentAccountResponseResource = studentAccountService.createStudentAccountResponse(studentAccountSignUpResource);
        ResponseEntity<StudentAccountResponseResource> responseEntity = new ResponseEntity<>(studentAccountResponseResource, HttpStatus.CREATED);
        StudentAccountResponseResource studentAccountResponse = responseEntity.getBody();
        return studentAccountResponse;
        //return new ResponseEntity<StudentAccountResponseResource>(studentAccountResponseResource, HttpStatus.CREATED);
    }
}
