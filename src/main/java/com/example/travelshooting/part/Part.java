package com.example.travelshooting.part;

import com.example.travelshooting.common.BaseEntity;
import com.example.travelshooting.product.Product;
import com.example.travelshooting.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_part")
@Getter
@NoArgsConstructor
public class Part extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leisure_product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private LocalTime openAt;

    @Column(nullable = false)
    private LocalTime closeAt;

    @Column(nullable = false)
    private int number;

    @OneToMany(mappedBy = "part")
    private List<Reservation> reservations = new ArrayList<>();
}
