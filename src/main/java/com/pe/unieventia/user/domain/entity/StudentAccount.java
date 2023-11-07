package com.pe.unieventia.user.domain.entity;

import java.time.LocalDateTime;

import com.pe.unieventia.student.domain.entity.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "googleInfoId")
    private GoogleInfo googleInfo;
    private String password;
    private LocalDateTime creationDateTime;
}
