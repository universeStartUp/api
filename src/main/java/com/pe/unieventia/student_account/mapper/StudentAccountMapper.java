package com.pe.unieventia.student_account.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student.dto.input.StudentAccountDTO;
import com.pe.unieventia.student_account.domain.entity.StudentAccount;
import com.pe.unieventia.student_account.dto.SignUpResponseDTO;
import com.pe.unieventia.student_account.dto.StudentAccountResponseDTO;

@Component
public class StudentAccountMapper {
    private final ModelMapper modelMapper;

    public StudentAccountMapper(ModelMapper modelMapper) {
        modelMapper.typeMap(StudentAccount.class, SignUpResponseDTO.class)
            .addMapping(
                src -> src.getEmail().getEmailAdress(),
                SignUpResponseDTO::setEmail
            );

        this.modelMapper = modelMapper;
    }
    
    public StudentAccount dtoToEntity(StudentAccountDTO studentAccountDto) {
        return modelMapper.map(studentAccountDto, StudentAccount.class);
    }

    public StudentAccountDTO entityToDto(StudentAccount student) {
        return modelMapper.map(student, StudentAccountDTO.class);
    }

    public StudentAccountResponseDTO entityToResponseDto(StudentAccount studentAccount) {
        return modelMapper.map(studentAccount, StudentAccountResponseDTO.class);
    }

    public List<StudentAccount> dtoListToEntityList(List<StudentAccountDTO> studentDtos) {
        return studentDtos
                .stream()
                .map(this::dtoToEntity)
                .toList();
    }

    public List<StudentAccountDTO> entityListToDtoList(List<StudentAccount> studentAccounts) {
        return studentAccounts
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    public List<StudentAccountResponseDTO> entityListToResponseDtoList(List<StudentAccount> studentAccounts) {
        return studentAccounts
                .stream()
                .map(this::entityToResponseDto)
                .toList();
    }
    public SignUpResponseDTO studentAccountToSignUpResponseDto(StudentAccount studentAccount) {
        return modelMapper.map(studentAccount, SignUpResponseDTO.class);
    }
}
