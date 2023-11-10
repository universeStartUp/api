package com.pe.unieventia.event.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "EventNetworks")
@AllArgsConstructor
@NoArgsConstructor
public class EventNetwork
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String TwitterURL;
    private String FacebookURL;
    private String InstagramURL;
}
