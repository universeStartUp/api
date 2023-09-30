package com.pe.unieventia.google_info.resource;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class GoogleInfoResource {
    @Column(length = 400)
    String accessToken;
    @Column(length = 400)
    String refreshToken;
}
