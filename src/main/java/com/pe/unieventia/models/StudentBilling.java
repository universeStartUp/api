package com.pe.unieventia.models;

import javax.persistence.*;

@Entity
@Table(name = "StudentBilling")
public class StudentBilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentBillingId;

    private Double amount; // Cambiado a Double porque JPA no tiene el tipo "money"

    //private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    // Getters, setters y otros métodos...
}