package com.pe.unieventia.models;

import javax.persistence.*;

@Entity
@Table(name = "AttendanceLists")
@IdClass(AttendanceId.class)
public class Attendance {

    @Id
    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    private Integer attendanceStateId;

    public void setStudent(Student student) {
        if (student != null) {
            this.student = student;
        } else {
            throw new IllegalArgumentException("Student cannot be null");
        }
    }

    public void setEvent(Event event) {
        if (event != null) {
            this.event = event;
        } else {
            throw new IllegalArgumentException("Event cannot be null");
        }
    }

    // Getters, setters
}

class AttendanceId {
    private Integer eventId;
    private Integer studentId;

}