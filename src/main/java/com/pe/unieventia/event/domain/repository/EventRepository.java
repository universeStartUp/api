package com.pe.unieventia.event.domain.repository;

import com.pe.unieventia.event.domain.entity.Event;
import com.pe.unieventia.event.domain.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    boolean existsByEventState_Id(Long eventState_id);
    List<Event> findAllByTitleContaining(String title);
    List<Event> findAllByEventState_Name(String name);
    List<Event> findAllByLocation_Id(Long id);
    List<Event> findAllByDate_Id(Long id);

    List<Event> findAllByEventCategoriesIn(Set<EventCategory> eventCategories);

}
