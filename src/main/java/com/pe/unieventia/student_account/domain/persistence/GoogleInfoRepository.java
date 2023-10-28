package com.pe.unieventia.student_account.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.student_account.domain.entity.GoogleInfo;

@Repository
public interface GoogleInfoRepository extends JpaRepository<GoogleInfo, Long> {
    
}
