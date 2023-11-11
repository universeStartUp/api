package com.pe.unieventia.student.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.student.domain.entity.Email;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    boolean existsByLocalAndEmailDomain_Domain(String local, String domain);
    Optional<Email> findByLocalAndEmailDomain_Domain(String local, String domain);
}
