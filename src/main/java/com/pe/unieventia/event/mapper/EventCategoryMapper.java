package com.pe.unieventia.event.mapper;

import com.pe.unieventia.event.dto.EventCategoryRequestDTO;
import com.pe.unieventia.event.dto.EventCategoryResponseDTO;
import com.pe.unieventia.event.domain.entity.EventCategory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventCategoryMapper {
    private final ModelMapper modelMapper;

    public EventCategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EventCategory resourceToEntity(EventCategoryRequestDTO eventCategoryRequestDTO) {
        return modelMapper.map(eventCategoryRequestDTO, EventCategory.class);
    }

    public EventCategoryRequestDTO entityToResource(EventCategory eventCategory) {
        return modelMapper.map(eventCategory, EventCategoryRequestDTO.class);
    }

    public EventCategoryResponseDTO entityToResponseResource(EventCategory eventCategory) {
        return modelMapper.map(eventCategory, EventCategoryResponseDTO.class);
    }

    public List<EventCategory> resourceListToEntityList(List<EventCategoryRequestDTO> eventCategoryRequestDTOS) {
        return eventCategoryRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<EventCategoryResponseDTO> entityListToResponseResourceList(List<EventCategory> eventCategories) {
        return eventCategories
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<EventCategoryRequestDTO> entityListToResourceList(List<EventCategory> eventCategories) {
        return eventCategories
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
