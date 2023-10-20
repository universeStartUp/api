package com.pe.unieventia.service;

import com.pe.unieventia.dto.EventStateRequestDTO;
import com.pe.unieventia.dto.EventStateResponseDTO;
import com.pe.unieventia.entity.EventState;
import com.pe.unieventia.expection.ResourceAlreadyExistsException;
import com.pe.unieventia.expection.ResourceNotFoundException;
import com.pe.unieventia.expection.ValidationException;
import com.pe.unieventia.mappers.EventStateMapper;
import com.pe.unieventia.repository.EventRepository;
import com.pe.unieventia.repository.EventStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventStateService {
    private final EventStateRepository repository;
    private final EventRepository eventRepository;
    private final EventStateMapper mapper;

    public EventStateResponseDTO createEventState(EventStateRequestDTO dto) {
        EventState eventState = mapper.resourceToEntity(dto);
        if (repository.existsByName(dto.getName())) {
            throw new ResourceAlreadyExistsException("exists this state: " + dto.getName());
        }
        eventState = repository.save(eventState);
        return mapper.entityToResponseResource(eventState);
    }

    public EventStateResponseDTO updateEventState(Long id, EventStateRequestDTO dto) {
        Optional<EventState> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("event with this id is not found" + id);
        }
        if (repository.existsByName(dto.getName())) {
            throw new ResourceAlreadyExistsException("exists this state: " + dto.getName());
        }

        EventState updateState = optional.get();
        updateState.setName(dto.getName());

        return mapper.entityToResponseResource(updateState);
    }

    public void deleteEventState(Long id) {
        Optional<EventState> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("event with this id not found " + id);
        }
        if (eventRepository.existsByEventState_Id(id)) {
            throw new ValidationException("event state is using");
        }
        repository.deleteById(id);

    }

    public EventStateResponseDTO getEventState(Long id) {
        Optional<EventState> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("event with this id not found " + id);
        }
        EventState eventState = optional.get();
        return mapper.entityToResponseResource(eventState);

    }

    public List<EventStateResponseDTO> getAllEventState() {
        List<EventState> eventStates = repository.findAll();
        if (eventStates.isEmpty()) {
            throw new ValidationException("EventSates is empty");
        }
        return mapper.entityListToResponseResourceList(eventStates);
    }


}
