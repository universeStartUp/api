package com.pe.unieventia.student_account.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class GoogleInfoResponseDTO {
    Long googleInfoId;
    @Column(length = 400)
    String accessToken;
    @Column(length = 400)
    String refreshToken;
}
