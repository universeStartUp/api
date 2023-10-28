package com.pe.unieventia.event.domain.repository;

import com.pe.unieventia.event.domain.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    boolean existsByNameAndDepartment_Name(String name, String DepartmentName);
    Optional<District> findByNameAndDepartment_Name(String name, String DepartmentName);

}
