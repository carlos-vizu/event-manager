package com.vizu.eventmanager.infrastructure.repository;

import com.vizu.eventmanager.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.id = :id AND e.deleted = false")
    Event findActiveById(@Param("id") Long id);

    @Query("SELECT e FROM Event e WHERE e.deleted = false")
    Page<Event> findAllActives(Pageable pageable);

}
