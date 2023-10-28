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

        // Process
        String[] parts = emailAddress.split("@");

        when(emailRepository.existsByLocalAndEmailDomain_Domain(parts[0], parts[1])).thenReturn(Boolean.FALSE);

        University university = new University();
        university.setName("Universidad Internacional");
        university.setAbbreviation("UI");
        EmailDomain emailDomain = new EmailDomain();
        emailDomain.setDomain(parts[1]);
        emailDomain.setUniversity(university);

        when(emailDomainRepository.findByDomain(parts[1])).thenReturn(Optional.of(emailDomain));

        Email email = new Email();
        email.setLocal(parts[0]);
        email.setEmailDomain(emailDomain);

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
        String[] parts = emailAddress.split("@");

        when(emailRepository.existsByLocalAndEmailDomain_Domain(parts[0], parts[1])).thenReturn(Boolean.TRUE);

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
        String[] parts = emailAddress.split("@");

        when(emailRepository.existsByLocalAndEmailDomain_Domain(parts[0], parts[1])).thenReturn(Boolean.FALSE);
        when(emailDomainRepository.findByDomain(parts[1])).thenReturn(Optional.empty());

        // Act
        assertThrows(ValidationException.class, () -> {
                emailService.createEmail(emailAddress);
        });
    }
}
