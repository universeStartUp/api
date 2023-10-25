package com.pe.unieventia.student.domain.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EmailDomains")
public class EmailDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailDomainId;
    private String domain;
    @OneToOne
    @JoinColumn(name = "universityId")
    private University university;
}
