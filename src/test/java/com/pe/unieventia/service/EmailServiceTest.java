package com.pe.unieventia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ValidationException;
import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.EmailDomain;
import com.pe.unieventia.student.domain.entity.University;
import com.pe.unieventia.student.domain.persistence.EmailDomainRepository;
import com.pe.unieventia.student.domain.persistence.EmailRepository;
import com.pe.unieventia.student.domain.service.EmailService;

public class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailRepository emailRepository;
    @Mock
    private EmailDomainRepository emailDomainRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmail() {
        // Arrange
        String emailAddress = "michael@ui.edu.com";
        String universityName = "Universidad Internacional";
        String universityAbbreviation = "UI";

        // Process
        String[] parts = emailAddress.split("@");

        when(emailRepository.existsByLocalAndEmailDomain_Domain(parts[0], parts[1]))
                .thenReturn(Boolean.FALSE);

        University university = University.builder()
                .name(universityName)
                .abbreviation(universityAbbreviation)
                .build();
        EmailDomain emailDomain = EmailDomain.builder()
                .domain(parts[1])
                .university(university)
                .build();

        when(emailDomainRepository.findByDomain(parts[1])).thenReturn(Optional.of(emailDomain));

        Email email = Email.builder()
                .local((parts[0]))
                .emailDomain(emailDomain)
                .build();

        when(emailRepository.save(email)).thenReturn(email);

        // Act
        Email result = emailService.createEmail(emailAddress);
        assertEquals(email, result);
    }

    @Test
    public void testCreateEmailAlreadyExist() {
        // Arrange
        String emailAddress = "michael@ui.edu.com";

        // Process
        String[] emailParts = emailAddress.split("@");

        when(emailRepository.existsByLocalAndEmailDomain_Domain(emailParts[0], emailParts[1]))
                .thenReturn(Boolean.TRUE);

        // Act
        assertThrows(
            ResourceAlreadyExistsException.class,
            () -> {
                emailService.createEmail(emailAddress);
            }
        );
    }

    @Test
    public void testCreateEmailInvalidDomain() {
        // Arrange
        String emailAddress = "michael@ui.edu.com";

        // Process
        String[] emailParts = emailAddress.split("@");

        when(emailRepository.existsByLocalAndEmailDomain_Domain(emailParts[0], emailParts[1]))
                .thenReturn(Boolean.FALSE);
        when(emailDomainRepository.findByDomain(emailParts[1]))
                .thenReturn(Optional.empty());

        // Act
        assertThrows(ValidationException.class, () -> {
                emailService.createEmail(emailAddress);
        });
    }
}
