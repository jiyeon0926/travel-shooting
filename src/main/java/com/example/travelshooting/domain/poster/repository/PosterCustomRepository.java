package com.example.travelshooting.domain.poster.repository;

import com.example.travelshooting.domain.poster.dto.PosterResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PosterCustomRepository {

    Page<PosterResDto> findPosters(Integer minExpenses, Integer maxExpenses, LocalDate travelStartAt, LocalDate travelEndAt, Integer days, Integer month, Pageable pageable);
}