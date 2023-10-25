package com.pe.unieventia.student.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.dto.EmailResponseDTO;

@Component
public class EmailMapper {
    private final ModelMapper modelMapper;

    public EmailMapper(ModelMapper modelMapper) {
        modelMapper
            .typeMap(Email.class, EmailResponseDTO.class)
            .addMapping(src -> src.getEmailDomain().getDomain(), EmailResponseDTO::setDomain)
            .addMapping(src -> src.getEmailDomain().getUniversity(), EmailResponseDTO::setUniversity);

        this.modelMapper = modelMapper;
    }
    public EmailResponseDTO entityToResponseDTO(Email email) {
        return modelMapper.map(email, EmailResponseDTO.class);
    }
}
