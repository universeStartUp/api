package com.pe.unieventia.models;

import javax.persistence.*;

@Entity
@Table(name = "StudentAccounts")
public class StudentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentAccountId;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    private String password;
    private Integer googleInfoId;
    //private Timestamp creationDate;

    // Getters, setters y otros métodos...
}