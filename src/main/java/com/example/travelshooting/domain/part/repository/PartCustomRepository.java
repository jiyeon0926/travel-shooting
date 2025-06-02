package com.example.travelshooting.domain.part.repository;

import com.example.travelshooting.domain.part.entity.Part;

public interface PartCustomRepository {

    Part findPartByProductIdAndUserIdAndId(Long productId, Long userId, Long partId);
}
