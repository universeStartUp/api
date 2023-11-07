package com.pe.unieventia.student.domain.service;

import com.pe.unieventia.user.domain.entity.User;
import org.springframework.stereotype.Service;

import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.persistence.StudentRepository;
import com.pe.unieventia.student.dto.StudentResponseDTO;
import com.pe.unieventia.student.mapper.StudentMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final EmailService emailService;
    
    @Transactional
    public Student createStudent(
        String surname,
        String studentCode,
        User user
    ) {
        Student student = Student
                .builder()
                .user(user)
                .surname(surname)
                .studentCode(studentCode)
                .build();

        return studentRepository.save(student);
    }

    @Transactional
    public StudentResponseDTO createStudentResponseDto(
        String surname,
        String firstName,
        User user
    ) {
        return studentMapper.entityToResponseDto(
                this.createStudent(
                        surname,
                        firstName,
                        user
                )
        );
    }
    
}
