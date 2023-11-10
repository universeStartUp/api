package com.pe.unieventia.controller;

import com.pe.unieventia.event.api.EventController;
import com.pe.unieventia.event.api.EventNetworkController;
import com.pe.unieventia.event.domain.service.EventNetworkService;
import com.pe.unieventia.event.domain.service.EventService;
import com.pe.unieventia.event.dto.EventNetworkRequestDTO;
import com.pe.unieventia.event.dto.EventNetworkResponseDTO;
import com.pe.unieventia.event.dto.EventResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EventNetworkTest {
    @InjectMocks
    private EventNetworkController eventNetworkController;
    @Mock
    private EventNetworkService eventNetworkService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEventNetworkById() {
        Long id = 1L;
        EventNetworkResponseDTO eventNetworkResponseDTO = new EventNetworkResponseDTO();
        eventNetworkResponseDTO.setId(id);
        when(eventNetworkService.getEventNetworkById(id)).thenReturn(eventNetworkResponseDTO);
        ResponseEntity<EventNetworkResponseDTO> responseEntity = eventNetworkController.getEventNetwork(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventNetworkResponseDTO.getId(), id);
    }

    @Test
    public void testGetAllEventNetworks() {
        List<EventNetworkResponseDTO> eventNetworkResponseDTOList = new ArrayList<>();
        when(eventNetworkService.getAllEventNetworks()).thenReturn(eventNetworkResponseDTOList);
        ResponseEntity<List<EventNetworkResponseDTO>> responseEntity = eventNetworkController.getAllEventNetworks();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventNetworkResponseDTOList, responseEntity.getBody());
    }

    @Test
    public void testCreateEventNetwork() {
        EventNetworkRequestDTO eventNetworkRequestDTO = new EventNetworkRequestDTO();
        eventNetworkRequestDTO.setFacebookURL("facebook.com");
        eventNetworkRequestDTO.setTwitterURL("twitter.com");
        eventNetworkRequestDTO.setInstagramURL("instagram.com");
        EventNetworkResponseDTO eventNetworkResponseDTO = new EventNetworkResponseDTO();
        when(eventNetworkService.createEventNetwork(eventNetworkRequestDTO)).thenReturn(eventNetworkResponseDTO);
        ResponseEntity<EventNetworkResponseDTO> responseEntity = eventNetworkController.createEventNetwork(eventNetworkRequestDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(eventNetworkResponseDTO, responseEntity.getBody());
    }

    @Test
    public void testUpdateEventNetwork() {
        Long id = 1L;
        EventNetworkRequestDTO eventNetworkRequestDTO = new EventNetworkRequestDTO();
        eventNetworkRequestDTO.setFacebookURL("facebook.com");
        eventNetworkRequestDTO.setTwitterURL("twitter.com");
        eventNetworkRequestDTO.setInstagramURL("instagram.com");
        EventNetworkResponseDTO eventNetworkResponseDTO = new EventNetworkResponseDTO();
        when(eventNetworkService.updateEventNetwork(id, eventNetworkRequestDTO)).thenReturn(eventNetworkResponseDTO);
        ResponseEntity<EventNetworkResponseDTO> responseEntity = eventNetworkController.updateEventNetwork(id, eventNetworkRequestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventNetworkResponseDTO, responseEntity.getBody());
    }
}
