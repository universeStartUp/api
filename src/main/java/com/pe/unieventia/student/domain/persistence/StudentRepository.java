package com.pe.unieventia.student.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.unieventia.student.domain.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(Long studentId);
    Boolean existsStudentByUser_UserId(Long userId);
}
