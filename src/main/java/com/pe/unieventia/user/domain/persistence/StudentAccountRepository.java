package com.pe.unieventia.user.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.user.domain.entity.StudentAccount;


@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, Long> {
    
}
