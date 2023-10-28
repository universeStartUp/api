package com.pe.unieventia.event.mapper;

import com.pe.unieventia.event.dto.LocationRequestDTO;
import com.pe.unieventia.event.dto.LocationResponseDTO;
import com.pe.unieventia.event.domain.entity.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationMapper {
    private final ModelMapper modelMapper;

    public LocationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Location resourceToEntity(LocationRequestDTO locationRequestDTO) {
        return modelMapper.map(locationRequestDTO, Location.class);
    }

    public LocationRequestDTO entityToResource(Location location) {
        return modelMapper.map(location, LocationRequestDTO.class);
    }

    public LocationResponseDTO entityToResponseResource(Location location) {
        return modelMapper.map(location, LocationResponseDTO.class);
    }

    public List<Location> resourceListToEntityList(List<LocationRequestDTO> locationRequestDTOS) {
        return locationRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<LocationResponseDTO> entityListToResponseResourceList(List<Location> locations) {
        return locations
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<LocationRequestDTO> entityListToResourceList(List<Location> locations) {
        return locations
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
