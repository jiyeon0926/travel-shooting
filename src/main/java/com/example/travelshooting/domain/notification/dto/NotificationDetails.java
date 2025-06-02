package com.example.travelshooting.domain.notification.dto;

import com.example.travelshooting.global.enums.NotificationType;

public record NotificationDetails(String subject, NotificationType type) {
}