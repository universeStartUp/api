package com.pe.unieventia.event.domain.repository;

import com.pe.unieventia.event.domain.entity.EventState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventStateRepository extends JpaRepository<EventState,Long> {
    Boolean existsByName(String name);

    
    Optional<EventState> findByName(String name);
}
