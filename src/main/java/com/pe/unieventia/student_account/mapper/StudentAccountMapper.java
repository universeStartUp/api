package com.pe.unieventia.student_account.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student_account.domain.entity.StudentAccount;
import com.pe.unieventia.student_account.resource.StudentAccountResource;
import com.pe.unieventia.student_account.resource.StudentAccountResponseResource;

@Component
public class StudentAccountMapper {
    private final ModelMapper modelMapper;

    public StudentAccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    public StudentAccount resourceToEntity(StudentAccountResource studentAccountResource) {
        return modelMapper.map(studentAccountResource, StudentAccount.class);
    }

    public StudentAccountResource entityToResource(StudentAccount student) {
        return modelMapper.map(student, StudentAccountResource.class);
    }

    public StudentAccountResponseResource entityToResponseResource(StudentAccount studentAccount) {
        return modelMapper.map(studentAccount, StudentAccountResponseResource.class);
    }

    public List<StudentAccount> resourceListToEntityList(List<StudentAccountResource> studentResources) {
        return studentResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<StudentAccountResource> entityListToResourceList(List<StudentAccount> studentAccounts) {
        return studentAccounts
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<StudentAccountResponseResource> entityListToResponseResourceList(List<StudentAccount> studentAccounts) {
        return studentAccounts
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
