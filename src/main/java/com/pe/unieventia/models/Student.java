package com.pe.unieventia.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String surname;
    private String firstname;
    private String lastname;
    private String studentCode;
    private String phoneNumber;
}