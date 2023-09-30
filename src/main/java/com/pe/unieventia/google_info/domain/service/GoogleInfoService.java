package com.pe.unieventia.google_info.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.unieventia.google_info.domain.entity.GoogleInfo;
import com.pe.unieventia.google_info.domain.persitence.GoogleInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleInfoService {
    private final GoogleInfoRepository googleInfoRepository;
    
    @Transactional
    public GoogleInfo createDefault() {
        return googleInfoRepository.save(new GoogleInfo());
    }
}
