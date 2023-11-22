package com.pe.unieventia.event.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "EventStates")
@AllArgsConstructor
@NoArgsConstructor
public class EventState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventStateId")
    private Long id;
    @Column(name = "state")
    private String name;
}
