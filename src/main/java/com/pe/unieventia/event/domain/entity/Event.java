package com.pe.unieventia.event.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name="date_id")
    private Date date;

    @ManyToOne
    @JoinColumn(name="state_id")
    private EventState eventState;

    @ManyToMany
    @JoinTable(name="event_event_categories",
    joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name="event_categories_id"))
    Set<EventCategory> eventCategories;

    @OneToOne
    @JoinColumn(name="event_network_id")
    private EventNetwork eventNetwork;
}
