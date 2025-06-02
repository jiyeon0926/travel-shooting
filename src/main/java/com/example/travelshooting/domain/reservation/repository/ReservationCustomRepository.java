package com.example.travelshooting.domain.reservation.repository;

import com.example.travelshooting.domain.reservation.dto.ReservationResDto;
import com.example.travelshooting.domain.reservation.entity.Reservation;
import com.example.travelshooting.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ReservationCustomRepository {

    Page<ReservationResDto> findAllByUserIdAndProductId(Long productId, User authenticatedUser, Pageable pageable);

    ReservationResDto findReservationByProductIdAndId(Long productId, Long reservationId, Long userId);

    Page<ReservationResDto> findPartnerReservationsByProductIdAndUserId(Long productId, User authenticatedUser, Pageable pageable);

    ReservationResDto findPartnerReservationByProductIdAndId(Long productId, Long reservationId, User authenticatedUser);

    Integer findTotalHeadCountByPartIdAndReservationDate(Long partId, LocalDate reservationDate);

    Reservation findReservationByProductIdAndIdAndUserId(Long productId, Long reservationId, Long userId);

    Reservation findPartnerReservationByProductIdAndIdAndUserId(Long productId, Long reservationId, User authenticatedUser);
}