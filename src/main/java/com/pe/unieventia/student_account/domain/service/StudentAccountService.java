package com.pe.unieventia.student_account.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.student_account.domain.entity.GoogleInfo;
import com.pe.unieventia.student_account.domain.entity.StudentAccount;
import com.pe.unieventia.student_account.domain.persistence.StudentAccountRepository;
import com.pe.unieventia.student_account.dto.StudentAccountResponseDTO;
import com.pe.unieventia.student_account.mapper.StudentAccountMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentAccountService {

    private final StudentService studentService;
    private final GoogleInfoService googleInfoService;
    
    private final StudentAccountRepository studentAccountRepository;
    private final StudentAccountMapper studentAccountMapper;
    
    @Transactional
    public StudentAccountResponseDTO createStudentAccount(
        String surname,
        String firstName,
        String lastName,
        String studentCode,
        String phoneNumber,
        String emailAddress,
        String password
    ) {
        Student student = studentService.createStudent(
            surname,
            firstName,
            lastName,
            studentCode,
            phoneNumber,
            emailAddress
        );
        GoogleInfo googleInfo = googleInfoService.createDefault();

        StudentAccount studentAccount = new StudentAccount();
        studentAccount.setStudent(student);
        studentAccount.setPassword(password);
        studentAccount.setGoogleInfo(googleInfo);
        studentAccount.setCreationDateTime(LocalDateTime.now());

        return studentAccountMapper.entityToResponseDto(studentAccountRepository.save(studentAccount));
    }
}
