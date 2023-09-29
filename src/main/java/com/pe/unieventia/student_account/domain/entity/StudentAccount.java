package com.pe.unieventia.student_account.domain.entity;

import com.pe.unieventia.email.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "StudentAccounts")
public class StudentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentAccountId;
    
    @OneToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @OneToOne
    private Email emailId;    
    
    private String password;
}
