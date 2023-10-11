package com.pe.unieventia.student.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.pe.unieventia.student.domain.entity.University;
import com.pe.unieventia.student.dto.input.UniversityDTO;

public class UniversityMapper {
    private final ModelMapper modelMapper;

    public UniversityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public University resourceToEntity(UniversityDTO universityResource) {
        return modelMapper.map(universityResource, University.class);
    }

    public UniversityDTO entityToResource(University university) {
        return modelMapper.map(university, UniversityDTO.class);
    }

    public List<University> resourceListToEntityList(List<UniversityDTO> universityResources) {
        return universityResources
            .stream()
            .map(this::resourceToEntity)
            .toList();
    }

    public List<UniversityDTO> entityListToResourceList(List<University> universities) {
        return universities
            .stream()
            .map(this::entityToResource)
            .toList();
    }
}
