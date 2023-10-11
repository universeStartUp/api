package com.pe.unieventia.student.domain.service;

import org.springframework.stereotype.Service;

import com.pe.unieventia.shared.exception.ResourceNotFoundException;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.persistence.StudentRepository;
import com.pe.unieventia.student.dto.input.StudentDTO;
import com.pe.unieventia.student.dto.response.StudentResponseDTO;
import com.pe.unieventia.student.mapper.StudentMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponseDTO getStudentById(Long studentId) {
        Student student = studentRepository
            .findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return studentMapper.entityToResponseDto(student);
    }
    
    @Transactional
    public Student createStudent(StudentDTO studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        return studentRepository.save(student);
    }
    
    public StudentResponseDTO createStudentResponse(StudentDTO studentDto) {
        return studentMapper.entityToResponseDto(createStudent(studentDto));
    }
}
