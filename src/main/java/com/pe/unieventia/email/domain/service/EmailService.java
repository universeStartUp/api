package com.pe.unieventia.email.domain.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.pe.unieventia.email.domain.entity.Email;
import com.pe.unieventia.email.domain.persistence.EmailRepository;
import com.pe.unieventia.email.mapper.EmailMapper;
import com.pe.unieventia.email.resource.EmailResponseResource;
import com.pe.unieventia.email_domain.domain.persistence.EmailDomainRepository;
import com.pe.unieventia.email_domain.domain.service.EmailDomainService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailMapper emailMapper;
    private final EmailRepository emailRepository;
    private final EmailDomainRepository emailDomainRepository;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean IsEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
/* 
    public Email CreateEmail(String emailStr) {
        if (IsEmail(emailStr)) {
            String[] parts = emailStr.split("@");
            if (emailRepository.existsByLocalAndEmailDomain_Domain(parts[0], parts[1])) {
                Long emailDomainId = emailDomainRepository.getIdByDomain(parts[1]);
            }
        }
        return 
    }
*/
}