package com.pe.unieventia.repository;

import com.pe.unieventia.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date,Long> {
    boolean existsByBeginDateAndEndDate(LocalDateTime beginDate, LocalDateTime endDate);

    Optional<Date> findByBeginDateAndEndDate(LocalDateTime beginDate,LocalDateTime endDate);

    List<Date> findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(LocalDateTime beginDate,LocalDateTime endDate);

}
