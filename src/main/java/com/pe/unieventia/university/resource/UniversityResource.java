package com.pe.unieventia.university.resource;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UniversityResource {
    private Long universityId;
    @Column(length = 50)
    private String name;
}
