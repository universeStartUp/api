package com.pe.unieventia.event.domain.service;

import com.pe.unieventia.event.domain.entity.EventNetwork;
import com.pe.unieventia.event.domain.repository.EventNetworkRepository;
import com.pe.unieventia.event.dto.EventNetworkRequestDTO;
import com.pe.unieventia.event.dto.EventNetworkResponseDTO;
import com.pe.unieventia.event.dto.EventResponseDTO;
import com.pe.unieventia.event.mapper.EventNetworkMapper;
import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ResourceNotFoundException;
import com.pe.unieventia.shared.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventNetworkService {
    private final EventNetworkRepository eventNetworkRepository;
    private final EventNetworkMapper eventNetworkMapper;

    public EventNetworkResponseDTO createEventNetwork(EventNetworkRequestDTO eventNetworkRequestDTO) {
        EventNetwork eventNetwork = eventNetworkMapper.resourceToEntity(eventNetworkRequestDTO);
        String twitterURL = eventNetwork.getTwitterURL();
        String facebookURL = eventNetwork.getFacebookURL();
        String instagramURL = eventNetwork.getInstagramURL();

        if (eventNetworkRepository.existsByTwitterURL(twitterURL)) {
            throw new ResourceAlreadyExistsException("exists this twitterURL: " + twitterURL);
        }
        if (eventNetworkRepository.existsByFacebookURL(facebookURL)) {
            throw new ResourceAlreadyExistsException("exists this facebookURL: " + facebookURL);
        }
        if (eventNetworkRepository.existsByInstagramURL(instagramURL)) {
            throw new ResourceAlreadyExistsException("exists this instagramURL: " + instagramURL);
        }
        if(twitterURL == null && facebookURL == null && instagramURL == null){
            throw new ValidationException("must have at least one social network");
        }

        eventNetwork = eventNetworkRepository.save(eventNetwork);
        return eventNetworkMapper.entityToResponseResource(eventNetwork);
    }

    public List<EventNetworkResponseDTO> getAllEventNetworks() {
        List<EventNetwork> eventNetworks = eventNetworkRepository.findAll();
        return eventNetworkMapper.entityListToResponseResourceList(eventNetworks);
    }
    public EventNetworkResponseDTO getEventNetworkById(Long id) {
        EventNetwork eventNetwork = eventNetworkRepository.findById(id)
                .orElseThrow(() -> new ResourceAlreadyExistsException("event network not found with id: " + id));
        return eventNetworkMapper.entityToResponseResource(eventNetwork);
    }
    public EventNetworkResponseDTO updateEventNetwork(Long id, EventNetworkRequestDTO eventNetworkRequestDTO) {
        EventNetwork eventNetwork = eventNetworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event network not found with id: " + id));
        String twitterURL = eventNetwork.getTwitterURL();
        String facebookURL = eventNetwork.getFacebookURL();
        String instagramURL = eventNetwork.getInstagramURL();

        if (eventNetworkRepository.existsByTwitterURL(twitterURL)) {
            throw new ResourceAlreadyExistsException("exists this twitterURL: " + twitterURL);
        }
        if (eventNetworkRepository.existsByFacebookURL(facebookURL)) {
            throw new ResourceAlreadyExistsException("exists this facebookURL: " + facebookURL);
        }
        if (eventNetworkRepository.existsByInstagramURL(instagramURL)) {
            throw new ResourceAlreadyExistsException("exists this instagramURL: " + instagramURL);
        }
        if(twitterURL == null && facebookURL == null && instagramURL == null){
            throw new ValidationException("must have at least one social network");
        }
        eventNetwork.setFacebookURL(eventNetworkRequestDTO.getFacebookURL());
        eventNetwork.setTwitterURL(eventNetworkRequestDTO.getTwitterURL());
        eventNetwork.setInstagramURL(eventNetworkRequestDTO.getInstagramURL());

        eventNetwork = eventNetworkRepository.save(eventNetwork);
        return eventNetworkMapper.entityToResponseResource(eventNetwork);
    }
    public void deleteEventNetwork(Long id) {
        EventNetwork eventNetwork = eventNetworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event network not found with id: " + id));
        eventNetworkRepository.deleteById(id);
    }


    
}
