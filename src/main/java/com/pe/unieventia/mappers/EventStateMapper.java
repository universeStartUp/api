package com.pe.unieventia.mappers;

import com.pe.unieventia.dto.EventStateRequestDTO;
import com.pe.unieventia.dto.EventStateResponseDTO;
import com.pe.unieventia.entity.EventState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventStateMapper {
    private final ModelMapper modelMapper;

    public EventStateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EventState resourceToEntity(EventStateRequestDTO eventStateRequestDTO) {
        return modelMapper.map(eventStateRequestDTO, EventState.class);
    }

    public EventStateRequestDTO entityToResource(EventState event) {
        return modelMapper.map(event, EventStateRequestDTO.class);
    }

    public EventStateResponseDTO entityToResponseResource(EventState event) {
        return modelMapper.map(event, EventStateResponseDTO.class);
    }

    public List<EventState> resourceListToEntityList(List<EventStateRequestDTO> eventStateRequestDTOS) {
        return eventStateRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<EventStateResponseDTO> entityListToResponseResourceList(List<EventState> eventStates) {
        return eventStates
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<EventStateRequestDTO> entityListToResourceList(List<EventState> eventStates) {
        return eventStates
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
