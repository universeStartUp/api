package com.pe.unieventia.config.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notification_config")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    private Boolean emailEnabled;
    @Getter @Setter
    private Boolean smsEnabled;
    @Getter @Setter
    private Boolean receiveNewsletter;
    @Getter @Setter
    private String languagePreference;
}
