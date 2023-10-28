package com.pe.unieventia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.unieventia.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Aquí puedes agregar métodos personalizados si los necesitas
}
