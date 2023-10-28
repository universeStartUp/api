package com.pe.unieventia.event.domain.service;

import com.pe.unieventia.event.dto.DistrictRequestDTO;
import com.pe.unieventia.event.dto.DistrictResponseDTO;
import com.pe.unieventia.event.domain.entity.Department;
import com.pe.unieventia.event.domain.entity.District;
import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ResourceNotFoundException;
import com.pe.unieventia.event.mapper.DistrictMapper;
import com.pe.unieventia.event.domain.repository.DepartmentRepository;
import com.pe.unieventia.event.domain.repository.DistrictRepository;
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

        Optional<District> district = repository.findByNameAndDepartment_Name(dto.getName(), dto.getDepartment().getName());
        if (district.isEmpty()) {
            throw new ResourceNotFoundException("District not found");
        }
        District resource = district.get();
        return mapper.entityToResponseResource(resource);
    }

    @Transactional
    public DistrictResponseDTO createDistrict(DistrictRequestDTO dto) {
        District resource = mapper.resourceToEntity(dto);

        if (repository.existsByNameAndDepartment_Name(dto.getName(), dto.getDepartment().getName())) {
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
        String strDepartment = requestDTO.getDepartment().getName();
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
