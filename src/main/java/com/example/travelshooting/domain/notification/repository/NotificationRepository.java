package com.example.travelshooting.domain.notification.repository;

import com.example.travelshooting.domain.notification.entity.Notification;
import com.example.travelshooting.global.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserIdAndStatus(Long userId, NotificationStatus status);
}