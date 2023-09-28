package com.pe.unieventia.email.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.email.domain.entity.Email;
import com.pe.unieventia.email.resource.EmailResource;

@Component
public class EmailMapper {
    private final ModelMapper modelMapper;
    public EmailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Email resourceToEntity(EmailResource emailResource) {
        return modelMapper.map(emailResource, Email.class);
    }
}
