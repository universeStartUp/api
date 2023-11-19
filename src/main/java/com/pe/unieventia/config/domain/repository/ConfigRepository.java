package com.pe.unieventia.config.domain.repository;

import com.pe.unieventia.config.domain.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long>{
}
