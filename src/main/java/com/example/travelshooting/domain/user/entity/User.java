package com.example.travelshooting.domain.user.entity;

import com.example.travelshooting.domain.comment.entity.Comment;
import com.example.travelshooting.domain.company.entity.Company;
import com.example.travelshooting.domain.like.entity.LikePoster;
import com.example.travelshooting.domain.poster.entity.Poster;
import com.example.travelshooting.domain.reservation.entity.Reservation;
import com.example.travelshooting.global.common.BaseEntity;
import com.example.travelshooting.global.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean isDeleted = false;

    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companies = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Poster> posters = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikePoster> likePosters = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public User(String email, String password, String name, UserRole role, String imageUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        if (!imageUrl.isEmpty()) {
            this.imageUrl = imageUrl;
        }
    }

    public void updateRole() {
        this.role = UserRole.PARTNER;
    }

    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
