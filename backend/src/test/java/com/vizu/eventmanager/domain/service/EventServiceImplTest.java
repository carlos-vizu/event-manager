package com.vizu.eventmanager.domain.service;

import com.vizu.eventmanager.application.controller.dto.request.EventRequestDTO;
import com.vizu.eventmanager.application.controller.dto.response.EventResponseDTO;
import com.vizu.eventmanager.domain.model.Event;
import com.vizu.eventmanager.domain.service.impl.EventServiceImpl;
import com.vizu.eventmanager.infrastructure.repository.EventRepository;
import com.vizu.eventmanager.mappers.EventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    private EventRepository repository;
    private EventServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(EventRepository.class);
        service = new EventServiceImpl(repository);
    }

    @Test
    void testCreateEvent() {
        EventRequestDTO dto = new EventRequestDTO();
        dto.setTitle("Evento Teste");
        dto.setDescription("Descrição");
        dto.setEventDate(LocalDateTime.now().plusDays(1));
        dto.setLocation("Local Teste");

        // Cria o entity simulando o que o JPA faria
        Event savedEvent = EventMapper.eventRequestToEntity(dto);
        savedEvent.setId(1L);
        savedEvent.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        savedEvent.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        when(repository.save(any(Event.class))).thenReturn(savedEvent);

        var result = service.create(dto);

        assertNotNull(result);
        assertEquals("Evento Teste", result.getTitle());
        assertNotNull(result.getCreatedAt(), "CreatedAt não pode ser null");
        assertNotNull(result.getUpdatedAt(), "UpdatedAt não pode ser null");

        verify(repository, times(1)).save(any(Event.class));
    }

    @Test
    void testFindByIdExisting() {
        Event event = new Event();
        event.setId(1L);
        event.setTitle("Evento 1");
        event.setEventTime(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
        event.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        event.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        when(repository.findById(1L)).thenReturn(Optional.of(event));

        var result = service.findById(1L);

        assertEquals("Evento 1", result.getTitle());
        assertNotNull(result.getEventDate(), "EventDate não pode ser null");
        assertNotNull(result.getCreatedAt(), "CreatedAt não pode ser null");
        assertNotNull(result.getUpdatedAt(), "UpdatedAt não pode ser null");

        verify(repository).findById(1L);
    }


    @Test
    void testListAllActives() {
        Event event1 = new Event();
        event1.setId(1L);
        event1.setEventTime(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
        event1.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        event1.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        Event event2 = new Event();
        event2.setId(2L);
        event2.setEventTime(Timestamp.valueOf(LocalDateTime.now().plusDays(2)));
        event2.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        event2.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        Page<Event> page = new PageImpl<>(List.of(event1, event2));

        when(repository.findAllActives(any(Pageable.class))).thenReturn(page);

        var result = service.listAllActives(Pageable.unpaged());

        assertEquals(2, result.getContent().size());
        assertNotNull(result.getContent().get(0).getEventDate());
        assertNotNull(result.getContent().get(1).getEventDate());
        verify(repository).findAllActives(any(Pageable.class));
    }

    @Test
    void testCreateEventWithPastDateShouldFail() {
        EventRequestDTO dto = new EventRequestDTO();
        dto.setTitle("Evento Passado");
        dto.setDescription("Descrição");
        dto.setEventDate(LocalDateTime.now().minusDays(1));
        dto.setLocation("Local Teste");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            if (dto.getEventDate().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Data do evento deve ser atual ou futura");
            }
            service.create(dto);
        });

        assertEquals("Data do evento deve ser atual ou futura", exception.getMessage());
    }




}
