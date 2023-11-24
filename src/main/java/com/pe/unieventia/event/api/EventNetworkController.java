package com.pe.unieventia.event.api;

import com.pe.unieventia.event.domain.service.EventNetworkService;
import com.pe.unieventia.event.dto.EventNetworkRequestDTO;
import com.pe.unieventia.event.dto.EventNetworkResponseDTO;
import com.pe.unieventia.event.dto.EventRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/event-networks")
@RequiredArgsConstructor
public class EventNetworkController {
    private final EventNetworkService eventNetworkService;
    @DeleteMapping("/{eventNetworkId}")
    public ResponseEntity<Void> deleteEventNetwork(Long eventNetworkId){
        eventNetworkService.deleteEventNetwork(eventNetworkId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/get/id/{id}")
    public ResponseEntity<EventNetworkResponseDTO> getEventNetwork(Long id){
        EventNetworkResponseDTO eventNetwork = eventNetworkService.getEventNetworkById(id);
        return new ResponseEntity<>(eventNetwork,HttpStatus.OK);
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<EventNetworkResponseDTO>> getAllEventNetworks(){
        List<EventNetworkResponseDTO> eventNetworks = eventNetworkService.getAllEventNetworks();
        return new ResponseEntity<>(eventNetworks,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventNetworkResponseDTO> createEventNetwork(
            @RequestBody EventNetworkRequestDTO eventNetworkRequestDTO) {
        EventNetworkResponseDTO eventNetwork = eventNetworkService.createEventNetwork(eventNetworkRequestDTO);
        return new ResponseEntity<>(eventNetwork, HttpStatus.CREATED);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<EventNetworkResponseDTO> updateEventNetwork(
            @PathVariable Long id,
            @RequestBody EventNetworkRequestDTO eventNetworkRequestDTO) {
        EventNetworkResponseDTO eventNetwork = eventNetworkService.updateEventNetwork(id, eventNetworkRequestDTO);
        return new ResponseEntity<>(eventNetwork, HttpStatus.OK);
    }

}
