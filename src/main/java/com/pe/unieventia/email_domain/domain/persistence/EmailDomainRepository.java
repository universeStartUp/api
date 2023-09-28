package com.pe.unieventia.email_domain.domain.persistence;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.email_domain.domain.entity.EmailDomain;
import java.util.Optional;


@Repository
public interface EmailDomainRepository extends JpaRepository<EmailDomain, Long> {
    //Optional<EmailDomain> findById(Long emailDomainId);
    boolean existsByDomain(String domain);
    Optional<Long> findIdByDomain(String domain);
}
