package com.pe.unieventia.student_account.domain.service;

import org.springframework.stereotype.Service;

import com.pe.unieventia.student_account.domain.entity.GoogleInfo;
import com.pe.unieventia.student_account.domain.persistence.GoogleInfoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GoogleInfoService {

    private final GoogleInfoRepository googleInfoRepository;

    @Transactional
    public GoogleInfo createDefault() {
        GoogleInfo googleInfo = new GoogleInfo();
        googleInfo.setAccessToken("0");
        googleInfo.setRefreshToken("0");

        return googleInfoRepository.save(googleInfo);
    }
}
