package com.pe.unieventia.student_account.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.unieventia.email.domain.entity.Email;
import com.pe.unieventia.email.domain.service.EmailService;
import com.pe.unieventia.google_info.domain.service.GoogleInfoService;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.student_account.domain.entity.StudentAccount;
import com.pe.unieventia.student_account.domain.persistence.StudentAccountRepository;
import com.pe.unieventia.student_account.mapper.StudentAccountMapper;
import com.pe.unieventia.student_account.resource.StudentAccountResponseResource;
import com.pe.unieventia.student_account.resource.SignUpResource;

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
    public StudentAccount createStudentAccount(SignUpResource signUpResource) {
        Email email = emailService.createEmail(signUpResource.getEmail());
        Student student = studentService.createStudent(signUpResource.getStudent());
        StudentAccount studentAccount = new StudentAccount(student, email, signUpResource.getPassword(), googleInfoService.createDefault());

        return studentAccountRepository.save(studentAccount);
    }

    public StudentAccountResponseResource createStudentAccountResponse(SignUpResource signUpResource) {
        return studentAccountMapper.entityToResponseResource(
            this.createStudentAccount(signUpResource)
        );
    }
}
