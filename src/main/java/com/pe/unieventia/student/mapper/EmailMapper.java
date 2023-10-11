package com.pe.unieventia.student.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.dto.input.EmailDTO;
import com.pe.unieventia.student.dto.response.EmailResumeResponseDTO;

@Component
public class EmailMapper {
    private final ModelMapper modelMapper;

    public EmailMapper(ModelMapper modelMapper) {
        modelMapper
            .typeMap(Email.class, EmailResumeResponseDTO.class)
            .addMapping(src -> src.getEmailDomain().getDomain(), EmailResumeResponseDTO::setDomain);

        this.modelMapper = modelMapper;
    }
    public Email resourceToEntity(EmailDTO emailResource) {
        return modelMapper.map(emailResource, Email.class);
    }
    public EmailResumeResponseDTO entityToResource(Email email) {
        return modelMapper.map(email, EmailResumeResponseDTO.class);
    }
}
