package com.pe.unieventia.notificationConfig.repository;

import com.pe.unieventia.notificationConfig.model.NotificationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationConfigRepository extends JpaRepository<NotificationConfig, Long> {

}