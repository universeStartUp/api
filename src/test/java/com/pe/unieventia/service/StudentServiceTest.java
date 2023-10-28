package com.pe.unieventia.service;

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
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";

        // Process
        String[] parts = emailAddress.split("@");
        University university = new University();
        university.setName("Universidad Internacional");
        university.setAbbreviation("UI");
        EmailDomain emailDomain = new EmailDomain();
        emailDomain.setDomain(parts[1]);
        emailDomain.setUniversity(university);
        Email email = new Email();
        email.setLocal(parts[0]);
        email.setEmailDomain(new EmailDomain());

        when(emailService.createEmail(emailAddress)).thenReturn(email);

        Student student = new Student();
        student.setSurname(surname);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setStudentCode(studentCode);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);

        when(studentRepository.save(student)).thenReturn(student);

        // Act
        Student result = studentService.createStudent(
                surname,
                firstName,
                lastName,
                studentCode,
                phoneNumber,
                emailAddress
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
    }
}
