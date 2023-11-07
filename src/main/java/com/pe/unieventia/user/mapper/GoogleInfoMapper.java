package com.pe.unieventia.user.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.user.domain.entity.GoogleInfo;
import com.pe.unieventia.user.dto.GoogleInfoDTO;
import com.pe.unieventia.user.dto.GoogleInfoResponseDTO;

@Component
public class GoogleInfoMapper {
    private final ModelMapper modelMapper;

    public GoogleInfoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GoogleInfo resourceToEntity(GoogleInfoDTO googleInfoResource) {
        return modelMapper.map(googleInfoResource, GoogleInfo.class);
    }

    public GoogleInfoDTO entityToResource(GoogleInfo googleInfo) {
        return modelMapper.map(googleInfo, GoogleInfoDTO.class);
    }

    public GoogleInfoResponseDTO entityToResponseResource(GoogleInfo googleInfo) {
        return modelMapper.map(googleInfo, GoogleInfoResponseDTO.class);
    }
}
