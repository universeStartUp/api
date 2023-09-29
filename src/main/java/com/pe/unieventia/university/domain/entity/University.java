package com.pe.unieventia.university.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long universityId;
    private String name;
}
