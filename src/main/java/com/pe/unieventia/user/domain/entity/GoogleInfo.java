package com.pe.unieventia.user.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GoogleInfo")
public class GoogleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long googleInfoId;
    String accessToken;
    String refreshToken;
}
