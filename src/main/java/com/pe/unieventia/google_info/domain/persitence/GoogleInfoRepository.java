package com.pe.unieventia.google_info.domain.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.google_info.domain.entity.GoogleInfo;

@Repository
public interface GoogleInfoRepository extends JpaRepository<GoogleInfo, Long> {
    
}
