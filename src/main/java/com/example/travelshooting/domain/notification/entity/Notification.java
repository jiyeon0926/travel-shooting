package com.example.travelshooting.domain.notification.entity;

import com.example.travelshooting.domain.user.entity.User;
import com.example.travelshooting.global.enums.DomainType;
import com.example.travelshooting.global.enums.NotificationStatus;
import com.example.travelshooting.global.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private DomainType domainType;

    @Column(nullable = false)
    private Long fkId;

    @Column(nullable = false, length = 30)
    private String subject;

    @Column(nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private NotificationStatus status = NotificationStatus.PENDING;

    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private NotificationType notificationType;

    @CreatedDate
    private LocalDateTime createdAt;

    private boolean isDeleted = false;

    public Notification(User user, DomainType domainType, Long fkId, String subject, NotificationStatus status, NotificationType notificationType) {
        this.user = user;
        this.domainType = domainType;
        this.fkId = fkId;
        this.subject = subject;
        this.status = status;
        this.notificationType = notificationType;
    }

    public void updateNotification() {
        this.isDeleted = true;
    }
}
