package com.pe.unieventia.student.domain.service;

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
        String firstName,
        String lastName,
        String studentCode,
        String phoneNumber,
        String emailAddress
    ) {
        Email email = emailService.createEmail(emailAddress);
        Student student = new Student();
        student.setSurname(surname);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setStudentCode(studentCode);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);

        return studentRepository.save(student);
    }

    @Transactional
    public StudentResponseDTO createStudentResponseDto(
        String surname,
        String firstName,
        String lastName,
        String studentCode,
        String phoneNumber,
        String emailAddress
    ) {
        return studentMapper.entityToResponseDto(createStudent(
                surname,
                firstName,
                lastName,
                studentCode,
                phoneNumber,
                emailAddress
            )
        );
    }
    
}
