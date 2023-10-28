package com.pe.unieventia.models;

import javax.persistence.*;

@Entity
@Table(name = "AttendanceLists")
@IdClass(AttendanceListId.class)
public class AttendanceList {

    @Id
    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    private Integer attendanceStateId;

    // Getters, setters y otros métodos...
}

class AttendanceListId {
    private Integer eventId;
    private Integer studentId;

    // equals, hashCode y otros métodos...
}
