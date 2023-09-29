package com.pe.unieventia.email.domain.service;

import org.springframework.stereotype.Service;

import com.pe.unieventia.email.domain.entity.Email;
import com.pe.unieventia.email.domain.persistence.EmailRepository;
import com.pe.unieventia.email.mapper.EmailMapper;
import com.pe.unieventia.email.resource.EmailResponseResource;
import com.pe.unieventia.email_domain.domain.entity.EmailDomain;
import com.pe.unieventia.email_domain.domain.persistence.EmailDomainRepository;
//import com.pe.unieventia.email_domain.domain.service.EmailDomainService;
import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ValidationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailMapper emailMapper;
    private final EmailRepository emailRepository;
    private final EmailDomainRepository emailDomainRepository;

    public Email createEmail(String emailStr) {
        String[] parts = emailStr.split("@");
        if (emailRepository.existsByLocalAndEmailDomain_Domain(parts[0], parts[1])) {
            throw new ResourceAlreadyExistsException("El correo" + emailStr + "ya fue registrado previamente en la aplicación.");
        }
        else {
            EmailDomain emailDomain = emailDomainRepository.findByDomain(parts[1])
                .orElseThrow(() -> new ValidationException("El dominio de correo: " + parts[1] + " no es valido"));
            Email email = new Email();
            email.setLocal(parts[0]);
            email.setEmailDomain(emailDomain);
            return email;
        }

    }
    public EmailResponseResource createEmailResponse(String emailString) {
        Email email = this.createEmail(emailString);
        return emailMapper.entityToResource(email);
    }

}