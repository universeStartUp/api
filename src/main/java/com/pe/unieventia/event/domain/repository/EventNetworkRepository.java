package com.pe.unieventia.event.domain.repository;

import com.pe.unieventia.event.domain.entity.EventNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventNetworkRepository extends JpaRepository<EventNetwork,Long> {
    boolean existsByFacebookURL(String url);
    boolean existsByTwitterURL(String url);
    boolean existsByInstagramURL(String url);
    Optional<EventNetwork> findByInstagramURLOrFacebookURLOrTwitterURL(String instagramURL, String facebookURL, String twitterURL);
}
