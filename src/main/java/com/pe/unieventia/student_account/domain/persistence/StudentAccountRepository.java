package com.pe.unieventia.student_account.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.student_account.domain.entity.StudentAccount;


@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, Long> {
    
}
