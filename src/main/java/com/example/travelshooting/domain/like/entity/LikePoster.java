package com.example.travelshooting.domain.like.entity;

import com.example.travelshooting.domain.poster.entity.Poster;
import com.example.travelshooting.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likePoster")
@Getter
@NoArgsConstructor
public class LikePoster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private Poster poster;

    public LikePoster(User user, Poster poster) {
        this.user = user;
        this.poster = poster;
    }

}
