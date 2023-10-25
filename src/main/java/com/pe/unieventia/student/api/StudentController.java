package com.pe.unieventia.student.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.unieventia.student.domain.service.EmailService;
import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.student.dto.StudentDTO;
import com.pe.unieventia.student.dto.StudentResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    StudentService studentService;
    EmailService emailService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> signUp(@Valid @RequestBody StudentDTO studentDto) {
        StudentResponseDTO studentResponseDTO = studentService.createStudentResponseDto(
            studentDto.getSurname(),
            studentDto.getFirstName(),
            studentDto.getLastName(),
            studentDto.getStudentCode(),
            studentDto.getPhoneNumber(),
            studentDto.getEmailAddress()
        );
        
        return new ResponseEntity<StudentResponseDTO>(studentResponseDTO, HttpStatus.CREATED);
    }
}
