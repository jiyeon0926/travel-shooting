package com.example.travelshooting.domain.poster.entity;

import com.example.travelshooting.domain.comment.entity.Comment;
import com.example.travelshooting.domain.file.entity.PosterFile;
import com.example.travelshooting.domain.like.entity.LikePoster;
import com.example.travelshooting.domain.payment.entity.Payment;
import com.example.travelshooting.domain.restaurant.entity.Restaurant;
import com.example.travelshooting.domain.user.entity.User;
import com.example.travelshooting.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poster")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE poster SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Poster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private Integer expenses;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime travelStartAt;

    private LocalDateTime travelEndAt;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikePoster> likePosters = new ArrayList<>();

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PosterFile> files = new ArrayList<>();

    @Builder
    public Poster(User user, Restaurant restaurant, Payment payment, Integer expenses, String title, String content, LocalDateTime travelStartAt, LocalDateTime travelEndAt) {
        this.user = user;
        this.restaurant = restaurant;
        this.payment = payment;
        this.expenses = expenses;
        this.title = title;
        this.content = content;
        this.travelStartAt = travelStartAt;
        this.travelEndAt = travelEndAt;
    }

    public void updateRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void updatePayment(Payment payment) {
        this.payment = payment;
    }

    public void updateExpenses(int expenses) {
        this.expenses = expenses;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateTravelStartAt(LocalDateTime travelStartAt) {
        this.travelStartAt = travelStartAt;
    }

    public void updateTravelEndAt(LocalDateTime travelEndAt) {
        this.travelEndAt = travelEndAt;
    }

}
