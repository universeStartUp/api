package com.pe.unieventia.student_account.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student_account.domain.entity.GoogleInfo;
import com.pe.unieventia.student_account.dto.GoogleInfoDTO;
import com.pe.unieventia.student_account.dto.GoogleInfoResponseDTO;

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
