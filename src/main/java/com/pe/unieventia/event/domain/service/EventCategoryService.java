package com.pe.unieventia.event.domain.service;

import com.pe.unieventia.event.dto.EventCategoryRequestDTO;
import com.pe.unieventia.event.dto.EventCategoryResponseDTO;
import com.pe.unieventia.event.domain.entity.EventCategory;
import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ResourceNotFoundException;
import com.pe.unieventia.shared.exception.ValidationException;
import com.pe.unieventia.event.mapper.EventCategoryMapper;
import com.pe.unieventia.event.domain.repository.EventCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventCategoryService {
    private final EventCategoryRepository eventCategoryRepository;
    private final EventCategoryMapper eventCategoryMapper;

    public EventCategoryResponseDTO createEventCategory(EventCategoryRequestDTO dto){
        boolean existsByName= eventCategoryRepository.existsByName(dto.getName());
        if(existsByName){
            throw new ResourceAlreadyExistsException("This catogory exist");
        }
        EventCategory eventCategory = new EventCategory();
        eventCategory.setName(dto.getName());
        eventCategory = eventCategoryRepository.save(eventCategory);
        return eventCategoryMapper.entityToResponseResource(eventCategory);
    }
    public EventCategoryResponseDTO updateEventCateogory(Long id,EventCategoryRequestDTO eventCategoryRequestDTO){
        Optional<EventCategory> optionalEventCategory =eventCategoryRepository.findById(id);
        if(optionalEventCategory.isEmpty()){
            throw new ResourceNotFoundException("event categories not exists with this id " + id);
        }
        EventCategory eventCategory= optionalEventCategory.get();
        eventCategory.setName(eventCategoryRequestDTO.getName());
        eventCategory = eventCategoryRepository.save(eventCategory);
        return eventCategoryMapper.entityToResponseResource(eventCategory);
    }
    public EventCategoryResponseDTO getEventCategory(Long id){
        Optional<EventCategory> optionalEventCategory = eventCategoryRepository.findById(id);
        if(optionalEventCategory.isEmpty()){
            throw new ResourceNotFoundException("event categories not exists with this id " + id);
        }
        return eventCategoryMapper.entityToResponseResource(optionalEventCategory.get());
    }
    public List<EventCategoryResponseDTO> getAllEventCategories(){
        List<EventCategory> eventCategories = eventCategoryRepository.findAll();

        if(eventCategories.isEmpty()){
            throw new ValidationException("event categories is empty");
        }
        return eventCategoryMapper.entityListToResponseResourceList(eventCategories);
    }
    public void deleteEventCategory(Long id){
        Optional<EventCategory> optionalEventCategory = eventCategoryRepository.findById(id);
        if(optionalEventCategory.isEmpty()){
            throw new ResourceNotFoundException("event catogories with this id not exists"+id);
        }
        EventCategory eventCategory = optionalEventCategory.get();
        if(!eventCategory.getEvents().isEmpty()){
            throw new ValidationException("you cant delete this category because exists someone events with this category");
        }
        eventCategoryRepository.deleteById(id);
    }
}
