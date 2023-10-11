package com.pe.unieventia.student.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ValidationException;
import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.EmailDomain;
import com.pe.unieventia.student.domain.persistence.EmailDomainRepository;
import com.pe.unieventia.student.domain.persistence.EmailRepository;
import com.pe.unieventia.student.dto.response.EmailResumeResponseDTO;
import com.pe.unieventia.student.mapper.EmailMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailMapper emailMapper;
    private final EmailRepository emailRepository;
    private final EmailDomainRepository emailDomainRepository;

    @Transactional
    public Email createEmail(String emailStr) {
        String[] parts = emailStr.split("@");
        if (emailRepository.existsByLocalAndEmailDomain_Domain(parts[0], parts[1])) {
            throw new ResourceAlreadyExistsException("Email is already registered:" + emailStr);
        } else {
            EmailDomain emailDomain = emailDomainRepository
                .findByDomain(parts[1])
                .orElseThrow(() -> new ValidationException("Invalid email domain : " + parts[1]));
            Email email = new Email();
            email.setLocal(parts[0]);
            email.setEmailDomain(emailDomain);
            return emailRepository.save(email);
        }
    }

    public EmailResumeResponseDTO createEmailResponse(String emailString) {
        Email email = this.createEmail(emailString);
        return emailMapper.entityToResource(email);
    }
}