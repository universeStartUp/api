package com.pe.unieventia.event.domain.service;

import com.pe.unieventia.event.domain.entity.Date;
import com.pe.unieventia.event.domain.entity.*;
import com.pe.unieventia.event.domain.repository.*;
import com.pe.unieventia.event.dto.*;
import com.pe.unieventia.event.mapper.EventMapper;
import com.pe.unieventia.event.mapper.EventNetworkMapper;
import com.pe.unieventia.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final DateRepository dateRepository;
    private final LocationRepository locationRepository;
    private final EventStateRepository eventStateRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final EventMapper eventMapper;
    private final EventNetworkMapper eventNetworkMapper;
    private final EventNetworkRepository eventNetworkRepository;

    public EventResponseDTO createEvent(EventRequestDTO eventRequestDTO) {

        DateRequestDTO dateRequestDTO = eventRequestDTO.getDate();
        LocationRequestDTO locationRequestDTO = eventRequestDTO.getLocation();
        EventStateRequestDTO eventStateRequestDTO = eventRequestDTO.getEventState();
        Set<EventCategoryRequestDTO> eventCategoryRequestDTOS = eventRequestDTO.getEventCategories();
        EventNetworkRequestDTO eventNetworkRequestDTO = eventRequestDTO.getEventNetwork();


        Date date = dateRepository.findByBeginDateAndEndDate(
                        dateRequestDTO.getBeginDate(), dateRequestDTO.getEndDate())
                .orElseThrow(() -> new ResourceNotFoundException("date not found"));
        Location location = locationRepository.findByAddressAndDistrict_NameAndDistrict_Department_Name(
                        locationRequestDTO.getAddress(), locationRequestDTO.getDistrict().getName(),
                        locationRequestDTO.getDistrict().getDepartment().getName())
                .orElseThrow(() -> new ResourceNotFoundException("location not found"));

        EventState eventState = eventStateRepository.findByName(eventStateRequestDTO.getName())
                .orElseThrow(() -> new ResourceNotFoundException("event state not found"));

        Set<EventCategory> eventCategories = new HashSet<>();
        for (EventCategoryRequestDTO eventCategoryRequestDTO : eventCategoryRequestDTOS) {
            eventCategories.add(eventCategoryRepository.findByName(eventCategoryRequestDTO.getName())
                    .orElseThrow(() -> new ResourceNotFoundException("event category not found")));
        }


       EventNetwork eventNetwork = eventNetworkRepository.findByInstagramURLOrFacebookURLOrTwitterURL(
                        eventNetworkRequestDTO.getInstagramURL(), eventNetworkRequestDTO.getFacebookURL(),
                        eventNetworkRequestDTO.getTwitterURL())
                .orElseThrow(() -> new ResourceNotFoundException("event network not found"));



        Event event = new Event();

        event.setTitle(eventRequestDTO.getTitle());
        event.setDescription(eventRequestDTO.getDescription());
        event.setDate(date);
        event.setLocation(location);
        event.setEventState(eventState);
        event.setEventCategories(eventCategories);
        event.setEventNetwork(eventNetwork);

        event = eventRepository.save(event);
        return eventMapper.entityToResponseResource(event);
    }

    public void deleteEvent(long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new ResourceNotFoundException("event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    public EventResponseDTO updateEvent(Long id, EventRequestDTO eventRequestDTO) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new ResourceNotFoundException("event not found with id: " + id);
        }
        DateRequestDTO dateRequestDTO = eventRequestDTO.getDate();
        LocationRequestDTO locationRequestDTO = eventRequestDTO.getLocation();
        EventStateRequestDTO eventStateRequestDTO = eventRequestDTO.getEventState();
        Set<EventCategoryRequestDTO> eventCategoryRequestDTOS = eventRequestDTO.getEventCategories();
        EventNetworkRequestDTO eventNetworkRequestDTO = eventRequestDTO.getEventNetwork();

        Date date = dateRepository.findByBeginDateAndEndDate(
                        dateRequestDTO.getBeginDate(), dateRequestDTO.getEndDate())
                .orElseThrow(() -> new ResourceNotFoundException("date not found"));
        Location location = locationRepository.findByAddressAndDistrict_NameAndDistrict_Department_Name(
                        locationRequestDTO.getAddress(), locationRequestDTO.getDistrict().getName(),
                        locationRequestDTO.getDistrict().getDepartment().getName())
                .orElseThrow(() -> new ResourceNotFoundException("location not found"));
        EventState eventState = eventStateRepository.findByName(eventStateRequestDTO.getName())
                .orElseThrow(() -> new ResourceNotFoundException("event state not found"));
        Set<EventCategory> eventCategories = new HashSet<>();
        for (EventCategoryRequestDTO eventCategoryRequestDTO : eventCategoryRequestDTOS) {
            eventCategories.add(eventCategoryRepository.findByName(eventCategoryRequestDTO.getName())
                    .orElseThrow(() -> new ResourceNotFoundException("event category not found")));
        }
        EventNetwork eventNetwork = eventNetworkRepository.findByInstagramURLOrFacebookURLOrTwitterURL(
                        eventNetworkRequestDTO.getInstagramURL(), eventNetworkRequestDTO.getFacebookURL(),
                        eventNetworkRequestDTO.getTwitterURL())
                .orElseThrow(() -> new ResourceNotFoundException("event network not found"));


        Event updateEvent = event.get();
        updateEvent.setTitle(eventRequestDTO.getTitle());
        updateEvent.setDescription(eventRequestDTO.getDescription());
        updateEvent.setDate(date);
        updateEvent.setLocation(location);
        updateEvent.setEventState(eventState);
        updateEvent.setEventCategories(eventCategories);
        updateEvent.setEventNetwork(eventNetwork);
        updateEvent = eventRepository.save(updateEvent);



        return eventMapper.entityToResponseResource(updateEvent);
    }

    public EventResponseDTO getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new ResourceNotFoundException("event not found with id: " + id);
        }
        return eventMapper.entityToResponseResource(event.get());
    }


    public List<EventResponseDTO> getEventByTitle(String title) {
        List<Event> events = eventRepository.findAllByTitleContaining(title);
        if (events.isEmpty()) {
            throw new ResourceNotFoundException("event not found with name: " + title);
        }
        List<EventResponseDTO> eventResponseDTOS = new ArrayList<>();
        for (Event event : events) {
            eventResponseDTOS.add(eventMapper.entityToResponseResource(event));
        }
        return eventResponseDTOS;
    }

    public List<EventResponseDTO> getEventByEventState(String name) {
        List<Event> events = eventRepository.findAllByEventState_Name(name);
        if (events.isEmpty()) {
            throw new ResourceNotFoundException("event not found with name: " + name);
        }
        List<EventResponseDTO> eventResponseDTOS = new ArrayList<>();
        for (Event event : events) {
            eventResponseDTOS.add(eventMapper.entityToResponseResource(event));
        }
        return eventResponseDTOS;
    }


    public List<EventResponseDTO> getEventByLocation(LocationRequestDTO locationRequestDTO) {

        Location location = locationRepository.findByAddressAndDistrict_NameAndDistrict_Department_Name(
                        locationRequestDTO.getAddress(), locationRequestDTO.getDistrict().getName(),
                        locationRequestDTO.getDistrict().getDepartment().getName())
                .orElseThrow(() -> new ResourceNotFoundException("location not found"));


        List<Event> events = eventRepository.findAllByLocation_Id(location.getId());
        if (events.isEmpty()) {
            throw new ResourceNotFoundException("events not found with location: " + location.getAddress());
        }
        List<EventResponseDTO> eventResponseDTOS = new ArrayList<>();
        for (Event event : events) {
            eventResponseDTOS.add(eventMapper.entityToResponseResource(event));
        }
        return eventResponseDTOS;
    }


    public List<EventResponseDTO> getEventByDate(DateRequestDTO dateRequestDTO) {

        List<Date> dates = dateRepository.findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(
                dateRequestDTO.getBeginDate(), dateRequestDTO.getEndDate());
        if (dates.isEmpty()) {
            throw new ResourceNotFoundException("date not found between " + dateRequestDTO.getBeginDate() + " and " + dateRequestDTO.getEndDate());
        }
        List<Event> events = new ArrayList<>();
        for (Date date : dates) {
            events.addAll(eventRepository.findAllByDate_Id(date.getId()));
        }

        return eventMapper.entityListToResponseResourceList(events);
    }

    public List<EventResponseDTO> getEventByCategories(List<EventCategoryRequestDTO> categories) {
        Set<EventCategory> eventCategories = new HashSet<>();
        for (EventCategoryRequestDTO eventCategoryRequestDTO : categories) {
            eventCategories.add(eventCategoryRepository.findByName(eventCategoryRequestDTO.getName())
                    .orElseThrow(() -> new ResourceNotFoundException("event category not found")));
        }
        List<Event> events = eventRepository.findAllByEventCategoriesIn(eventCategories);
        if (events.isEmpty()) {
            throw new ResourceNotFoundException("event not found with categories: " + categories);
        }

        return eventMapper.entityListToResponseResourceList(events);
    }

    public EventNetworkResponseDTO getEventNetworkByEventId(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new ResourceNotFoundException("event not found with id: " + id);
        }
        Event myEvent = event.get();
        EventNetwork eventNetwork = myEvent.getEventNetwork();
        return eventNetworkMapper.entityToResponseResource(eventNetwork);
    }

    

}
