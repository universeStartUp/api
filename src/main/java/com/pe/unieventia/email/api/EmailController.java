package com.pe.unieventia.email.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.unieventia.email.domain.service.EmailService;
import com.pe.unieventia.email.resource.EmailCreateResource;
import com.pe.unieventia.email.resource.EmailResponseResource;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailResponseResource> createEmail(EmailCreateResource emailCreateResource) {
        EmailResponseResource studentResponseResource = emailService.createEmailResponse(emailCreateResource.getEmail());
        return new ResponseEntity<>(studentResponseResource, HttpStatus.OK);
    }
}
