package com.pe.unieventia.student.domain.service;

import com.pe.unieventia.security.domain.entity.User;
import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

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
    public Student signUp(
        User user,
        String surname,
        String studentCode
    ) {
        if (studentRepository.existsStudentByUser_UserId(user.getUserId())) {
            throw new ResourceAlreadyExistsException("User with id" + user.getUserId() + " is already registered as student.");
        } else {
            Student student = Student.builder()
                    .user(user)
                    .surname(surname)
                    .studentCode(studentCode)
                    .build();

            return studentRepository.save(student);
        }
    }

    /*@Transactional
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
    }*/

}
