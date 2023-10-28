package com.pe.unieventia.student_account.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student_account.domain.entity.StudentAccount;
import com.pe.unieventia.student_account.dto.StudentAccountResponseDTO;

@Component
public class StudentAccountMapper {
    private final ModelMapper modelMapper;

    public StudentAccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    public StudentAccountResponseDTO entityToResponseDto(StudentAccount studentAccount) {
        return modelMapper.map(studentAccount, StudentAccountResponseDTO.class);
    }

    public List<StudentAccountResponseDTO> entityListToResponseDtoList(List<StudentAccount> studentAccounts) {
        return studentAccounts
                .stream()
                .map(this::entityToResponseDto)
                .toList();
    }

}
