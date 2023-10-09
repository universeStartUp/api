package com.pe.unieventia.mappers;

import com.pe.unieventia.dto.DistrictRequestDTO;
import com.pe.unieventia.dto.DistrictResponseDTO;
import com.pe.unieventia.entity.District;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistrictMapper {

    private final ModelMapper modelMapper;

    public DistrictMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public District resourceToEntity(DistrictRequestDTO departmentRequestDTO) {
        return modelMapper.map(departmentRequestDTO, District.class);
    }

    public DistrictRequestDTO entityToResource(District district) {
        return modelMapper.map(district, DistrictRequestDTO.class);
    }

    public DistrictResponseDTO entityToResponseResource(District district) {
        return modelMapper.map(district, DistrictResponseDTO.class);
    }

    public List<District> resourceListToEntityList(List<DistrictRequestDTO> districtRequestDTOS) {
        return districtRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<DistrictResponseDTO> entityListToResponseResourceList(List<District> districts) {
        return districts
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<DistrictRequestDTO> entityListToResourceList(List<District> districts) {
        return districts
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
