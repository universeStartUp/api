package com.pe.unieventia.event.mapper;

import com.pe.unieventia.event.dto.EventRequestDTO;
import com.pe.unieventia.event.dto.EventResponseDTO;
import com.pe.unieventia.event.domain.entity.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventMapper {
    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Event resourceToEntity(EventRequestDTO eventRequestDTO) {
        return modelMapper.map(eventRequestDTO, Event.class);
    }

    public EventRequestDTO entityToResource(Event event) {
        return modelMapper.map(event, EventRequestDTO.class);
    }

    public EventResponseDTO entityToResponseResource(Event event) {
        return modelMapper.map(event, EventResponseDTO.class);
    }

    public List<Event> resourceListToEntityList(List<EventRequestDTO> eventRequestDTOS) {
        return eventRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<EventResponseDTO> entityListToResponseResourceList(List<Event> events) {
        return events
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<EventRequestDTO> entityListToResourceList(List<Event> events) {
        return events
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
