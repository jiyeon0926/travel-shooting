package com.example.travelshooting.domain.like.repository;

import com.example.travelshooting.domain.like.entity.LikePoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikePosterRepository extends JpaRepository<LikePoster, Long> {

    boolean existsByUserIdAndPosterId(Long userId, Long likePosterId);

    LikePoster findByUserIdAndPosterId(Long userId, Long posterId);

    List<LikePoster> findAllByUserId(Long userId);

}
