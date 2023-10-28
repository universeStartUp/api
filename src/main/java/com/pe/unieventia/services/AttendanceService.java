package com.pe.unieventia.services;

import com.pe.unieventia.models.Attendance;
import com.pe.unieventia.models.Event;
import com.pe.unieventia.models.Student;
import com.pe.unieventia.repository.AttendanceRepository;
import com.pe.unieventia.repository.EventRepository;
import com.pe.unieventia.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;

    public Attendance registerStudent(Long studentId, Long eventId) {
        // Obtener el estudiante y el evento de sus respectivos repositorios
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Verificar si el estudiante ya está registrado en el evento
        List<Attendance> existingAttendances = attendanceRepository.findByStudentIdAndEventId(studentId, eventId);
        if (!existingAttendances.isEmpty()) {
            throw new RuntimeException("Student already registered for this event");
        }

        // Crear y guardar una nueva asistencia
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setEvent(event);
        attendanceRepository.save(attendance);

        return attendance;
    }
}