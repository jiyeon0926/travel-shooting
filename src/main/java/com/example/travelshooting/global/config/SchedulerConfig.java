package com.example.travelshooting.global.config;

import com.example.travelshooting.domain.notification.service.NotificationService;
import com.example.travelshooting.domain.reservation.service.ReservationService;
import com.example.travelshooting.domain.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {

    private final RestaurantService restaurantService;
    private final ReservationService reservationService;
    private final NotificationService notificationService;

    // 음식점 정보 자동 저장
    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void runOnce() {
        restaurantService.saveGgRestaurants(1, 999);
    }

    // 예약 만료 처리
    @Scheduled(cron = "0 0 18 * * ?")
    public void cancelExpiredReservations() {
        reservationService.cancelExpiredReservations();
    }

    // 30일 지난 알림 삭제
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteNotification() {
        notificationService.deleteNotification();
    }
}