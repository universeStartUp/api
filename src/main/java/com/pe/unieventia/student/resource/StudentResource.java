package com.pe.unieventia.student.resource;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentResource {
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 30)
    private String phoneNumber;
    @Column(length = 15)
    private String studentCode;
}
