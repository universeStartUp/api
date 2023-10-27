package com.pe.unieventia.service;

import com.pe.unieventia.dto.LocationRequestDTO;
import com.pe.unieventia.dto.LocationResponseDTO;
import com.pe.unieventia.entity.District;
import com.pe.unieventia.entity.Location;
import com.pe.unieventia.expection.ResourceAlreadyExistsException;
import com.pe.unieventia.expection.ResourceNotFoundException;
import com.pe.unieventia.mappers.LocationMapper;
import com.pe.unieventia.repository.DepartmentRepository;
import com.pe.unieventia.repository.DistrictRepository;
import com.pe.unieventia.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;
    private final DistrictRepository districtRepository;

    public List<LocationResponseDTO> getAllLocations() {
        List<Location> list = repository.findAll();
        return mapper.entityListToResponseResourceList(list);
    }

    public  LocationResponseDTO createLocation( LocationRequestDTO dto){

        String districtName = dto.getDistrict().getName();
        String departmentName = dto.getDistrict().getDepartment().getName();

        Optional<District> optionalDistrict =districtRepository.findByNameAndDepartment_Name(districtName,departmentName);
        if(optionalDistrict.isEmpty()){
            throw new ResourceNotFoundException("District or Departnement not found");
        }

        Optional<Location> existsThisLocation  = repository.findByAddressAndDistrict_NameAndDistrict_Department_Name(dto.getAddress(),districtName,departmentName);
        if(existsThisLocation.isPresent()){
            throw new ResourceAlreadyExistsException("This location already exists ");
        }

        District district = optionalDistrict.get();
        Location location  = mapper.resourceToEntity(dto);
        location.setDistrict(district);
        location = repository.save(location);
        return mapper.entityToResponseResource(location);
    }

    public LocationResponseDTO updateLocation(Long locationId, LocationRequestDTO dto) {
        Optional<Location> optionalLocation = repository.findById(locationId);
        if (optionalLocation.isEmpty()) {
            throw new ResourceNotFoundException("location not found with id: " + locationId);
        }
        Optional<District> optionalDistrict = districtRepository.findByNameAndDepartment_Name(dto.getDistrict().getName(), dto.getDistrict().getDepartment().getName());
        if (optionalDistrict.isEmpty()) {
            throw new ResourceNotFoundException("District and Deparmetn not found with names: ");

        }
        Location location = optionalLocation.get();
        location.setAddress(dto.getAddress());
        location.setDistrict(optionalDistrict.get());

        location = repository.save(location);
        return mapper.entityToResponseResource(location);
    }


    public LocationResponseDTO getLocationById(Long id) {
        Location location = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        return mapper.entityToResponseResource(location);
    }

    @Transactional
    public void deleteLocation(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Location not found with id: " + id);
        }

        repository.deleteById(id);
    }
}
