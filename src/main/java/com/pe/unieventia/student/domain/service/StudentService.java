package com.pe.unieventia.student.domain.service;

import org.springframework.stereotype.Service;

import com.pe.unieventia.shared.exception.ResourceNotFoundException;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.persistence.StudentRepository;
import com.pe.unieventia.student.mapper.StudentMapper;
import com.pe.unieventia.student.resource.StudentResource;
import com.pe.unieventia.student.resource.StudentResponseResource;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponseResource getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return studentMapper.entityToResponseResource(student);
    }
    
    @Transactional
    public StudentResponseResource createStudent(StudentResource studentResource) {
        Student student = studentMapper.resourceToEntity(studentResource);
        student = studentRepository.save(student);

        return studentMapper.entityToResponseResource(student);
    }
}
