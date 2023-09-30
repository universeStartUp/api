package com.pe.unieventia.google_info.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "GoogleInfo")
public class GoogleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long googleInfoId;
    String accessToken;
    String refreshToken;
    public GoogleInfo() {
        this.accessToken = "0";
        this.refreshToken = "0";
    }
}
