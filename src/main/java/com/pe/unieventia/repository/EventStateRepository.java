package com.pe.unieventia.repository;

import com.pe.unieventia.entity.EventState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStateRepository extends JpaRepository<EventState,Long> {
    Boolean existsByName(String name);
}
