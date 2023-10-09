package com.pe.unieventia.mappers;

import com.pe.unieventia.dto.DepartmentRequestDTO;
import com.pe.unieventia.dto.DepartmentResponseDTO;
import com.pe.unieventia.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentMapper {
    private final ModelMapper modelMapper;

    public DepartmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Department resourceToEntity(DepartmentRequestDTO departmentRequestDTO) {
        return modelMapper.map(departmentRequestDTO, Department.class);
    }

    public DepartmentRequestDTO entityToResource(Department department) {
        return modelMapper.map(department, DepartmentRequestDTO.class);
    }

    public DepartmentResponseDTO entityToResponseResource(Department department) {
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    public List<DepartmentResponseDTO> entityListToResponseResourceList(List<Department> departments) {
        return departments
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<Department> resourceListToEntityList(List<DepartmentRequestDTO> departmentRequestDTOS) {
        return departmentRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<DepartmentRequestDTO> entityListToResourceList(List<Department> departments) {
        return departments
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
