package com.pe.unieventia.user.mapper;

import com.pe.unieventia.user.domain.entity.User;
import com.pe.unieventia.user.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User mapToModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
