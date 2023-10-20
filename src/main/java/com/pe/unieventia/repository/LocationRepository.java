package com.pe.unieventia.repository;

import com.pe.unieventia.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Optional<Location> findByAddressAndDistrict_NameAndDistrict_Department_Name(String address, String district,String department);
}
