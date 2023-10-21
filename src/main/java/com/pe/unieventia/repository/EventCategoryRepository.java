package com.pe.unieventia.repository;

import com.pe.unieventia.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory,Long> {

    boolean existsByName(String name);
    Optional<EventCategory> findByName(String name);
}
