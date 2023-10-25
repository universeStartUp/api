package com.pe.unieventia.student.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.student.domain.entity.EmailDomain;

import java.util.Optional;


@Repository
public interface EmailDomainRepository extends JpaRepository<EmailDomain, Long> {
    Optional<EmailDomain> findById(Long emailDomainId);
    Optional<EmailDomain> findByDomain(String domain);
}
