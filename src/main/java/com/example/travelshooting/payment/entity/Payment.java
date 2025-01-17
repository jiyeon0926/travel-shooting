package com.example.travelshooting.payment.entity;

import com.example.travelshooting.common.BaseEntity;
import com.example.travelshooting.enums.PaymentStatus;
import com.example.travelshooting.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partner_order_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private String tid;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status = PaymentStatus.READY;

    @Column(nullable = false)
    private Long partnerUserId;

    @Column(nullable = false)
    private Integer totalPrice;

    private String paymentType;

    public Payment(Reservation reservation, String tid, Long partnerUserId, Integer totalPrice) {
        this.reservation = reservation;
        this.tid = tid;
        this.partnerUserId = partnerUserId;
        this.totalPrice = totalPrice;
    }

    public void updatePayment(PaymentStatus status, String paymentType) {
        this.status = status;
        this.paymentType = paymentType;
    }
}
