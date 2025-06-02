package com.example.travelshooting.domain.payment.repository;

import com.example.travelshooting.domain.payment.dto.PaymentResDto;
import com.example.travelshooting.domain.payment.entity.Payment;
import com.example.travelshooting.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentCustomRepository {

    Page<PaymentResDto> findPayments(User authenticatedUser, Pageable pageable);

    PaymentResDto findPaymentByIdAndUserId(Payment existingPayment, User authenticatedUser);
}