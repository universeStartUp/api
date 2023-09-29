package com.pe.unieventia.student.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.student.resource.StudentResource;
import com.pe.unieventia.student.resource.StudentResponseResource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseResource> getStudentById(@PathVariable Long studentId) {
        StudentResponseResource studentResponseResource = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentResponseResource, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<StudentResponseResource> createStudent(@Valid @RequestBody StudentResource studentResource) {
        StudentResponseResource studentResponseResource = studentService.createStudent(studentResource);
        return new ResponseEntity<>(studentResponseResource, HttpStatus.OK);
    }
}
