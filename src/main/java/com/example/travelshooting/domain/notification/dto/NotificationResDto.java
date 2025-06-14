package com.example.travelshooting.domain.notification.dto;

import com.example.travelshooting.global.common.BaseDtoDataType;
import com.example.travelshooting.global.enums.DomainType;
import com.example.travelshooting.global.enums.NotificationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class NotificationResDto implements BaseDtoDataType {

    private final Long id;

    private final DomainType domainType;

    private final Long fkId;

    private final String subject;

    private final NotificationType notificationType;

    private final LocalDateTime createdAt;
}
