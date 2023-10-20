package com.pe.unieventia.repository;

import com.pe.unieventia.entity.Event;
import com.pe.unieventia.entity.EventState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    boolean existsByEventState_Id(Long eventState_id);

}
