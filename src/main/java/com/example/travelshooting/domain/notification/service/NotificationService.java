package com.example.travelshooting.domain.notification.service;

import com.example.travelshooting.domain.notification.dto.NotificationResDto;
import com.example.travelshooting.domain.notification.entity.Notification;
import com.example.travelshooting.domain.notification.repository.NotificationRepository;
import com.example.travelshooting.domain.user.entity.User;
import com.example.travelshooting.domain.user.service.UserService;
import com.example.travelshooting.global.common.Const;
import com.example.travelshooting.global.enums.NotificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    @Transactional
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<NotificationResDto> findAllByUserIdAndStatus() {
        User user = userService.findAuthenticatedUser();
        List<Notification> notifications = notificationRepository.findAllByUserIdAndStatus(user.getId(), NotificationStatus.SENT);

        return notifications.stream().map(notification -> new NotificationResDto(
                notification.getId(),
                notification.getDomainType(),
                notification.getFkId(),
                notification.getSubject(),
                notification.getNotificationType(),
                notification.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteNotification() {
        List<Notification> notifications = notificationRepository.findAll();

        notifications.forEach(notification -> {
            LocalDateTime expirationTime = notification.getCreatedAt().plusDays(Const.NOTIFICATION_EXPIRED_DAY);

            if (LocalDateTime.now().isAfter(expirationTime)) {
                notification.updateNotification();
                notificationRepository.save(notification);
            }
        });
    }
}