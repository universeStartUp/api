package com.pe.unieventia.repository;

import com.pe.unieventia.entity.Event;
import com.pe.unieventia.entity.EventState;
import com.pe.unieventia.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    boolean existsByEventState_Id(Long eventState_id);
    List<Event> findAllByTitleContaining(String title);
    List<Event> findAllByEventState_Name(String name);
    List<Event> findAllByLocation_Id(Long id);
    List<Event> findAllByDate_Id(Long id);

}
