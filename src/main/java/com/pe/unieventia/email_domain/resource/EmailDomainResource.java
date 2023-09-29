package com.pe.unieventia.email_domain.resource;

import com.pe.unieventia.university.domain.entity.University;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EmailDomainResource {
    @Column(length = 255)
    private String domain;
    private University university;
}
