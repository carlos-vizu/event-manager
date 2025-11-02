package com.vizu.eventmanager.domain.service.impl;

import com.vizu.eventmanager.application.controller.dto.request.EventRequestDTO;
import com.vizu.eventmanager.application.controller.dto.response.EventResponseDTO;
import com.vizu.eventmanager.domain.model.Event;
import com.vizu.eventmanager.domain.service.EventService;
import com.vizu.eventmanager.infrastructure.repository.EventRepository;
import com.vizu.eventmanager.mappers.EventMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    public EventResponseDTO findById(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return EventMapper.entityToEventResponse(event);
    }

    public Page<EventResponseDTO> listAll(Pageable pageable) {
        Page<Event> events = repository.findAll(pageable);
        return events.map(EventMapper::entityToEventResponse);
    }

    public Page<EventResponseDTO> listAllActives(Pageable pageable) {
        Page<Event> events = repository.findAllActives(pageable);
        return events.map(EventMapper::entityToEventResponse);
    }

    @Transactional
    public EventResponseDTO create(EventRequestDTO dto) {
        Event entity = EventMapper.eventRequestToEntity(dto);
        Event saved = repository.save(entity);
        return EventMapper.entityToEventResponse(saved);
    }

    @Transactional
    public EventResponseDTO update(Long id, EventRequestDTO dto) {
        Event eventFound = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Event updated = EventMapper.eventUpdateRequestToEntity(dto, eventFound);
        updated.setId(id);
        updated.setCreatedAt(eventFound.getCreatedAt());
        updated.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        Event saved = repository.save(updated);
        return EventMapper.entityToEventResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        event.setDeleted(true);
        repository.save(event);
    }
}
