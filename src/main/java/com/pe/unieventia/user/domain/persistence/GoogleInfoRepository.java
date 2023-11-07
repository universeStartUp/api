package com.pe.unieventia.user.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.user.domain.entity.GoogleInfo;

@Repository
public interface GoogleInfoRepository extends JpaRepository<GoogleInfo, Long> {
    
}
