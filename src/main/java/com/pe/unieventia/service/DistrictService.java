package com.pe.unieventia.service;

import com.pe.unieventia.dto.DistrictRequestDTO;
import com.pe.unieventia.dto.DistrictResponseDTO;
import com.pe.unieventia.entity.Department;
import com.pe.unieventia.entity.District;
import com.pe.unieventia.expection.ResourceAlreadyExistsException;
import com.pe.unieventia.expection.ResourceNotFoundException;
import com.pe.unieventia.mappers.DepartmentMapper;
import com.pe.unieventia.mappers.DistrictMapper;
import com.pe.unieventia.repository.DepartmentRepository;
import com.pe.unieventia.repository.DistrictRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictMapper mapper;
    private final DistrictRepository repository;
    private  final DepartmentRepository departmentRepository;



    public List<DistrictResponseDTO> getAllDistricts() {
        List<District> list = repository.findAll();
        return mapper.entityListToResponseResourceList(list);
    }

    public DistrictResponseDTO getDistrictByName(DistrictRequestDTO dto) {

        Optional<District> district = repository.findByNameAndDepartment_Name(dto.getName(), dto.getDepartmentRequestDTO().getName());
        if (district.isEmpty()) {
            throw new ResourceNotFoundException("District not found");
        }
        District resource = district.get();
        return mapper.entityToResponseResource(resource);
    }

    @Transactional
    public DistrictResponseDTO createDistrict(DistrictRequestDTO dto) {
        District resource = mapper.resourceToEntity(dto);

        if (repository.existsByNameAndDepartment_Name(dto.getName(), dto.getDepartmentRequestDTO().getName())) {
            throw new ResourceAlreadyExistsException("District with this name and department name exists");
        }

        resource = repository.save(resource);
        return mapper.entityToResponseResource(resource);
    }


    @Transactional
    public void deleteDistrict(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("District not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public DistrictResponseDTO updateDistrict(Long id, DistrictRequestDTO requestDTO) {
        Optional<District> optionalDistrict = repository.findById(id);
        String strDepartment = requestDTO.getDepartmentRequestDTO().getName();
        Optional<Department> optionalDepartment = departmentRepository.findByName(strDepartment);

        if (optionalDistrict.isEmpty()) {
            throw new ResourceNotFoundException("District not found with id: " + id);
        }

        if(optionalDepartment.isEmpty()){
            throw new ResourceNotFoundException("Department not found with name: " + strDepartment);
        }

        District district = optionalDistrict.get();

        district = repository.save(district);
        return mapper.entityToResponseResource(district);

    }


    public DistrictResponseDTO getDistrictById(Long id) {
        District department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District not found with id: " + id));
        return mapper.entityToResponseResource(department);
    }
}