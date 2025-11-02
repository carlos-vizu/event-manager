package com.vizu.eventmanager.domain.service;

import com.vizu.eventmanager.application.controller.dto.request.EventRequestDTO;
import com.vizu.eventmanager.application.controller.dto.response.EventResponseDTO;
import com.vizu.eventmanager.domain.model.Event;
import com.vizu.eventmanager.infrastructure.repository.EventRepository;
import com.vizu.eventmanager.mappers.EventMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Page<EventResponseDTO> listAll(Pageable pageable);
    Page<EventResponseDTO> listAllActives(Pageable pageable);
    EventResponseDTO findById(Long id);
    EventResponseDTO create(EventRequestDTO dto);
    EventResponseDTO update(Long id, EventRequestDTO dto);
    void delete(Long id);
}
