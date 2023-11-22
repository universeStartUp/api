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
    @Column(name = "eventId")
    private  Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="locationsId")
    private Location location;

    @ManyToOne
    @JoinColumn(name="eventDateId")
    private Date date;

    @ManyToOne
    @JoinColumn(name="eventStateId")
    private EventState eventState;

    @ManyToMany
    @JoinTable(name="EventCategoriesLists",
    joinColumns = @JoinColumn(name = "eventId"),
    inverseJoinColumns = @JoinColumn(name="eventCategoryId"))
    Set<EventCategory> eventCategories;

    @OneToOne
    @JoinColumn(name="eventNetworkId")
    private EventNetwork eventNetwork;
}
