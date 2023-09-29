package com.pe.unieventia.email.domain.entity;

import com.pe.unieventia.email_domain.domain.entity.EmailDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    @ManyToOne
    @JoinColumn(name = "emailDomainId")
    private EmailDomain emailDomain;

    private String local;
}
