package com.pe.unieventia.service;

import com.pe.unieventia.dto.DepartmentRequestDTO;
import com.pe.unieventia.dto.DepartmentResponseDTO;
import com.pe.unieventia.entity.Department;
import com.pe.unieventia.expection.ResourceAlreadyExistsException;
import com.pe.unieventia.expection.ResourceNotFoundException;
import com.pe.unieventia.mappers.DepartmentMapper;
import com.pe.unieventia.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentMapper mapper;
    private final DepartmentRepository repository;

    public List<DepartmentResponseDTO> getAllDepartment() {
        List<Department> list = repository.findAll();
        return mapper.entityListToResponseResourceList(list);
    }
    @Transactional
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
        Department resource  = mapper.resourceToEntity(dto);

        if(repository.existsByName(dto.getName())){
            throw  new ResourceAlreadyExistsException("Department with this name exists");
        }

        resource = repository.save(resource);
        return mapper.entityToResponseResource(resource);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }

        repository.deleteById(id);
    }

    public DepartmentResponseDTO getDepartmentById(Long id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return mapper.entityToResponseResource(department);
    }



}
