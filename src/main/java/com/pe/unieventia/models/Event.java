package com.pe.unieventia.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    private String title;
    private String description;
}
