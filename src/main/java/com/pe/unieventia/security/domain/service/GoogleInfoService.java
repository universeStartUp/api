package com.pe.unieventia.security.domain.service;

import org.springframework.stereotype.Service;

import com.pe.unieventia.security.domain.entity.GoogleInfo;
import com.pe.unieventia.security.domain.persistence.GoogleInfoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GoogleInfoService {

    private final GoogleInfoRepository googleInfoRepository;

    @Transactional
    public GoogleInfo createDefault() {
        GoogleInfo googleInfo = GoogleInfo.builder()
                .accessToken("0")
                .refreshToken("0")
                .build();

        return googleInfoRepository.save(googleInfo);
    }
}
