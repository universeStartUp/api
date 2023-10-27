package com.pe.unieventia.controller;

import com.pe.unieventia.dto.*;
import com.pe.unieventia.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EventControllerTest {
    @InjectMocks
    private EventController eventController;
    @Mock
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEventByState() {
        EventStateRequestDTO eventState = new EventStateRequestDTO();
        eventState.setName("PLANIFICADO");

        List<EventResponseDTO> eventResponseDTOList = new ArrayList<>();


        when(eventService.getEventByEventState(eventState.getName())).thenReturn(eventResponseDTOList);

        ResponseEntity<List<EventResponseDTO>> responseEntity = eventController.getEventByState(eventState.getName());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventResponseDTOList, responseEntity.getBody());

    }

    @Test
    public void testGetEventByTitle() {
        String Title = "AI";

        List<EventResponseDTO> eventResponseDTOList = new ArrayList<>();


        when(eventService.getEventByTitle(Title)).thenReturn(eventResponseDTOList);

        ResponseEntity<List<EventResponseDTO>> responseEntity = eventController.getEvent(Title);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventResponseDTOList, responseEntity.getBody());

    }

    @Test
    public void testGetEventByLocation() {
        LocationRequestDTO location = new LocationRequestDTO();
        DistrictRequestDTO district = new DistrictRequestDTO();
        DepartmentRequestDTO department = new DepartmentRequestDTO();

        department.setName("LIMA");

        district.setName("CERCADO DE LIMA");
        district.setDepartment(department);

        location.setAddress("Universidad Nacional Mayor de San Marcos, Avenida Venezuela s/n");
        location.setDistrict(district);

        List<EventResponseDTO> eventResponseDTOList = new ArrayList<>();


        when(eventService.getEventByLocation(location)).thenReturn(eventResponseDTOList);

        ResponseEntity<List<EventResponseDTO>> responseEntity = eventController.getEventByLocation(location);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventResponseDTOList, responseEntity.getBody());
    }

    @Test
    public void testGetEventByDate() {
        DateRequestDTO dateRequestDTO = new DateRequestDTO();
        LocalDateTime beginDate = LocalDateTime.parse("2023-10-09T08:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-10-09T09:30:00");
        dateRequestDTO.setBeginDate(beginDate);
        dateRequestDTO.setEndDate(endDate);

        List<EventResponseDTO> eventResponseDTOList = new ArrayList<>();


        when(eventService.getEventByDate(dateRequestDTO)).thenReturn(eventResponseDTOList);

        ResponseEntity<List<EventResponseDTO>> responseEntity = eventController.getEventByDate(dateRequestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventResponseDTOList, responseEntity.getBody());

    }

    @Test
    public void testGetEventByCategories() {
        List<EventCategoryRequestDTO> categories = new ArrayList<>();
        EventCategoryRequestDTO eventCategoryRequestDTO = new EventCategoryRequestDTO();
        eventCategoryRequestDTO.setName("CONFERENCIA");
        categories.add(eventCategoryRequestDTO);
        List<EventResponseDTO> eventResponseDTOList = new ArrayList<>();
        when(eventService.getEventByCategories(categories)).thenReturn(eventResponseDTOList);

        ResponseEntity<List<EventResponseDTO>> responseEntity = eventController.getEventByCategory(categories);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventResponseDTOList, responseEntity.getBody());
    }


}
