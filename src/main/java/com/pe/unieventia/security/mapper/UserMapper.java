package com.pe.unieventia.security.mapper;

import com.pe.unieventia.security.domain.entity.User;
import com.pe.unieventia.security.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User mapToModel(UserResponseDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserResponseDTO entityToResponseDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
