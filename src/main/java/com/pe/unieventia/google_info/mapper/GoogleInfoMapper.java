package com.pe.unieventia.google_info.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.google_info.domain.entity.GoogleInfo;
import com.pe.unieventia.google_info.resource.GoogleInfoResource;
import com.pe.unieventia.google_info.resource.GoogleInfoResponseResource;

@Component
public class GoogleInfoMapper {
    private final ModelMapper modelMapper;

    public GoogleInfoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GoogleInfo resourceToEntity(GoogleInfoResource googleInfoResource) {
        return modelMapper.map(googleInfoResource, GoogleInfo.class);
    }

    public GoogleInfoResource entityToResource(GoogleInfo googleInfo) {
        return modelMapper.map(googleInfo, GoogleInfoResource.class);
    }

    public GoogleInfoResponseResource entityToResponseResource(GoogleInfo googleInfo) {
        return modelMapper.map(googleInfo, GoogleInfoResponseResource.class);
    }
}
