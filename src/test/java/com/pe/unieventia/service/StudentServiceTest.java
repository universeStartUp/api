package com.pe.unieventia.service;

import com.pe.unieventia.security.domain.entity.User;
import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ValidationException;
import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.EmailDomain;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.entity.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pe.unieventia.student.domain.persistence.StudentRepository;
import com.pe.unieventia.student.domain.service.EmailService;
import com.pe.unieventia.student.domain.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class StudentServiceTest {
    /*
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStudentSuccess() {
        // Arrange
        String surname = "Mitch";
        String studentCode = "u202015237";
        String emailAddress = "u202015237@ui.edu.com";
        String universityName = "Universidad Internacional";
        String universityAbbreviation = "UI";
        // Process
        String[] parts = emailAddress.split("@");
        University university = University
                .builder()
                .name(universityName)
                .abbreviation(universityAbbreviation)
                .build();
        EmailDomain emailDomain = EmailDomain
                .builder()
                .domain(parts[1])
                .university(university)
                .build();
        Email email = Email
                .builder()
                .local(parts[0])
                .emailDomain(emailDomain)
                .build();
        User user = new User();

        when(emailService.createEmail(emailAddress)).thenReturn(email);

        Student student = Student
                .builder()
                .surname(surname)
                .studentCode(studentCode)
                .user(user)
                .build();

        when(studentRepository.save(student)).thenReturn(student);

        // Act
        Student result = studentService.createStudent(
                surname,
                studentCode
        );

        assertEquals(student, result);
    }

    @Test
    public void testCreateStudentAlreadyExist() {
        // Arrange
        String surname = "Mitch";
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";

        // Process
        when(emailService.createEmail(emailAddress))
                .thenThrow(new ResourceAlreadyExistsException("Email " + emailAddress + " is already registered"));

        // Act
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            studentService.createStudent(
                    surname,
                    firstName,
                    lastName,
                    studentCode,
                    phoneNumber,
                    emailAddress
            );
        });
    }

    @Test
    public void testCreateStudentInvalidDomain() {
        // Arrange
        String surname = "Mitch";
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";

        // Process
        String[] parts = emailAddress.split("@");

        when(emailService.createEmail(emailAddress))
                .thenThrow(new ValidationException("Email domain " + parts[1] + " is not a valid domain"));

        // Act
        assertThrows(ValidationException.class, () -> {
            studentService.createStudent(
                    surname,
                    firstName,
                    lastName,
                    studentCode,
                    phoneNumber,
                    emailAddress
            );
        });
    }*/
}
