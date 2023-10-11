package com.pe.unieventia.student_account.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.unieventia.google_info.domain.service.GoogleInfoService;
import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.service.EmailService;
import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.student_account.domain.entity.StudentAccount;
import com.pe.unieventia.student_account.domain.persistence.StudentAccountRepository;
import com.pe.unieventia.student_account.dto.SignUpDTO;
import com.pe.unieventia.student_account.dto.SignUpResponseDTO;
import com.pe.unieventia.student_account.mapper.StudentAccountMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentAccountService {
    private final StudentAccountRepository studentAccountRepository;
    private final StudentService studentService;
    private final EmailService emailService;
    private final GoogleInfoService googleInfoService;
    private final StudentAccountMapper studentAccountMapper;
    
    @Transactional
    public StudentAccount createStudentAccount(SignUpDTO signUpResource) {
        Email email = emailService.createEmail(signUpResource.getEmail());
        Student student = studentService.createStudent(signUpResource.getStudent());
        StudentAccount studentAccount = new StudentAccount(student, email, signUpResource.getPassword(), googleInfoService.createDefault());

        return studentAccountRepository.save(studentAccount);
    }

    public SignUpResponseDTO createStudentAccountResponse(SignUpDTO signUpResource) {
        return studentAccountMapper.studentAccountToSignUpResponseDto(this.createStudentAccount(signUpResource));
    }
}
