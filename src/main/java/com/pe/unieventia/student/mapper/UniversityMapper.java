package com.pe.unieventia.student.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.pe.unieventia.student.domain.entity.University;
import com.pe.unieventia.student.dto.UniversityDTO;
import com.pe.unieventia.student.dto.UniversityResponseDTO;

public class UniversityMapper {
    private final ModelMapper modelMapper;

    public UniversityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public University dtoToEntity(UniversityDTO universityResource) {
        return modelMapper.map(universityResource, University.class);
    }

    public List<University> dtoListToEntityList(List<UniversityDTO> universityResources) {
        return universityResources
            .stream()
            .map(this::dtoToEntity)
            .toList();
    }

    public UniversityDTO entityToDto(University university) {
        return modelMapper.map(university, UniversityDTO.class);
    }

    public List<UniversityDTO> entityListToDtoList(List<University> universities) {
        return universities
            .stream()
            .map(this::entityToDto)
            .toList();
    }
    public UniversityResponseDTO entityToResponseDto(University university) {
        return modelMapper.map(university, UniversityResponseDTO.class);
    } 
}
