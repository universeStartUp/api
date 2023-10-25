package com.pe.unieventia.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pe.unieventia.student.domain.persistence.StudentRepository;
import com.pe.unieventia.student.domain.service.EmailService;
import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.student.mapper.StudentMapper;

public class StudentServiceTest {
    
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    private void testCreateStudent() {
        
    }
}
