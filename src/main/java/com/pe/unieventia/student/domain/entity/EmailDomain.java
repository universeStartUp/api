package com.pe.unieventia.student.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "EmailDomains")
public class EmailDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailDomainId;

    @OneToOne
    @JoinColumn(name = "universityId")
    private University university;

    private String domain;
}
