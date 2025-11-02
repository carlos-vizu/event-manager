package com.vizu.eventmanager.mappers;

import com.sun.jdi.request.EventRequest;
import com.vizu.eventmanager.application.controller.dto.request.EventRequestDTO;
import com.vizu.eventmanager.application.controller.dto.response.EventResponseDTO;
import com.vizu.eventmanager.domain.model.Event;

import java.sql.Timestamp;
import java.util.List;

public class EventMapper {

    private EventMapper() {
        super();
    }

    public static Event eventRequestToEntity(EventRequestDTO dto) {
        Event event = new Event(dto.getTitle(),
                dto.getDescription(),
                Timestamp.valueOf(dto.getEventDate()),
                dto.getLocation());
        return event;
    }

    public static Event eventUpdateRequestToEntity(EventRequestDTO dto, Event eventFound) {
        Event event = new Event(dto.getTitle(),
                dto.getDescription(),
                Timestamp.valueOf(dto.getEventDate()),
                dto.getLocation(),
                dto.isDeleted());
        return event;
    }

    public static EventResponseDTO entityToEventResponse(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventTime().toLocalDateTime());
        dto.setLocation(event.getLocation());
        dto.setDeleted(event.isDeleted());
        dto.setCreatedAt(event.getCreatedAt().toLocalDateTime());
        dto.setUpdatedAt(event.getUpdatedAt().toLocalDateTime());
        return dto;
    }

    public static List<EventResponseDTO> listEntityToListEventResponse(List<Event> events) {
        return events.stream()
                .map(event -> new EventResponseDTO(event.getId(), event.getTitle(),
                        event.getDescription(), event.getEventTime().toLocalDateTime(), event.getLocation(),
                        event.isDeleted(), event.getCreatedAt().toLocalDateTime(), event.getUpdatedAt().toLocalDateTime()))
                .toList();
    }

}
