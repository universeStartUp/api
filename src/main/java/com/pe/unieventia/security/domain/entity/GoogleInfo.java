package com.pe.unieventia.security.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GoogleInfo")
public class GoogleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long googleInfoId;
    String accessToken;
    String refreshToken;
}
