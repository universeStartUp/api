package com.pe.unieventia.email_domain.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

//import com.pe.unieventia.email_domain.domain.entity.EmailDomain;
import com.pe.unieventia.email_domain.domain.persistence.EmailDomainRepository;
//import com.pe.unieventia.email_domain.mapper.EmailDomainMapper;
//import com.pe.unieventia.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailDomainService {
    
    private final EmailDomainRepository emailDomainRepository;
    //private final EmailDomainMapper emailDomainMapper;

    public boolean isValidEmailDomain(String domain) {
        return emailDomainRepository.existsByDomain(domain);
    }
    public Long getIdByDomain(String domain) {
        Optional<Long> emailDomainId = emailDomainRepository.findIdByDomain(domain);
        return emailDomainId.orElse(null);
    }
}
