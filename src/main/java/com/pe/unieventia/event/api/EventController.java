package com.pe.unieventia.event.api;

import com.pe.unieventia.event.dto.*;
import com.pe.unieventia.event.domain.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO event = eventService.createEvent(eventRequestDTO);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long id,
            @RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO event = eventService.updateEvent(id, eventRequestDTO);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
    @GetMapping("/get/id/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(
            @PathVariable Long id) {
        EventResponseDTO event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/get/title/{title}")
    public ResponseEntity<List<EventResponseDTO>> getEvent(
            @PathVariable String title) {
        List<EventResponseDTO> event = eventService.getEventByTitle(title);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/get/state/{state}")
    public ResponseEntity<List<EventResponseDTO>> getEventByState(
            @PathVariable String state) {
        List<EventResponseDTO> event = eventService.getEventByEventState(state);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/get/location")
    public ResponseEntity<List<EventResponseDTO>> getEventByLocation(
            @RequestBody LocationRequestDTO location) {
        List<EventResponseDTO> event = eventService.getEventByLocation(location);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/get/date")
    public ResponseEntity<List<EventResponseDTO>> getEventByDate(
            @RequestBody DateRequestDTO dateRequestDTO) {
        List<EventResponseDTO> event = eventService.getEventByDate(dateRequestDTO);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/get/category")
    public ResponseEntity<List<EventResponseDTO>> getEventByCategory(
            @RequestBody List<EventCategoryRequestDTO> categories) {
        List<EventResponseDTO> event = eventService.getEventByCategories(categories);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/get/networks/{id}")
    public ResponseEntity<EventNetworkResponseDTO> getEventNetworks(
            @PathVariable Long id) {

        EventNetworkResponseDTO eventNetwork = eventService.getEventNetworkByEventId(id);
        return new ResponseEntity<>(eventNetwork, HttpStatus.OK);
    }

    

}
