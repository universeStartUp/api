package com.pe.unieventia.university.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.pe.unieventia.university.domain.entity.University;
import com.pe.unieventia.university.resource.UniversityResource;

public class UniversityMapper {
    private final ModelMapper modelMapper;

    public UniversityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public University resourceToEntity(UniversityResource universityResource) {
        return modelMapper.map(universityResource, University.class);
    }

    public UniversityResource entityToResource(University university) {
        return modelMapper.map(university, UniversityResource.class);
    }

    public List<University> resourceListToEntityList(List<UniversityResource> universityResources) {
        return universityResources
            .stream()
            .map(this::resourceToEntity)
            .toList();
    }

    public List<UniversityResource> entityListToResourceList(List<University> universities) {
        return universities
            .stream()
            .map(this::entityToResource)
            .toList();
    }
}
