package com.pe.unieventia.controllers;

import com.pe.unieventia.models.Attendance;
import com.pe.unieventia.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/register")
    public ResponseEntity<Attendance> registerStudent(@RequestParam Long studentId, @RequestParam Long eventId) {
        try {
            Attendance attendance = attendanceService.registerStudent(studentId, eventId);
            return new ResponseEntity<>(attendance, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}