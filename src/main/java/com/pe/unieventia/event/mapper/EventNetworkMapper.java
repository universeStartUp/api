package com.pe.unieventia.event.mapper;

import com.pe.unieventia.event.domain.entity.Date;
import com.pe.unieventia.event.domain.entity.EventNetwork;
import com.pe.unieventia.event.dto.DateRequestDTO;
import com.pe.unieventia.event.dto.DateResponseDTO;
import com.pe.unieventia.event.dto.EventNetworkRequestDTO;
import com.pe.unieventia.event.dto.EventNetworkResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventNetworkMapper {
    private final ModelMapper modelMapper;
    public EventNetworkMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public EventNetwork resourceToEntity(EventNetworkRequestDTO eventNetwork){
        return modelMapper.map(eventNetwork,EventNetwork.class);
    }

    public EventNetworkRequestDTO entityToResource (EventNetwork eventNetwork){
        return modelMapper.map(eventNetwork,EventNetworkRequestDTO.class);
    }

    public EventNetworkResponseDTO entityToResponseResource (EventNetwork eventNetwork){
        return modelMapper.map(eventNetwork,EventNetworkResponseDTO.class);
    }

    public List<EventNetworkResponseDTO> entityListToResponseResourceList (List<EventNetwork> eventNetworks){
        return eventNetworks
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<EventNetwork> resourceListToEntityList(List<EventNetworkRequestDTO> eventNetworkRequestDTOS){
        return eventNetworkRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<EventNetworkRequestDTO> entityListToResourceList(List<EventNetwork> eventNetworks){
        return eventNetworks
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
