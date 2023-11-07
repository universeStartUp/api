package com.pe.unieventia.user.domain.persistence;

import com.pe.unieventia.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail_LocalAndEmail_EmailDomain_Domain(String local, String domain);
}
