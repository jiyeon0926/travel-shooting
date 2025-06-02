package com.example.travelshooting.domain.file.repository;

import com.example.travelshooting.domain.file.entity.LeisureFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureFileRepository extends JpaRepository<LeisureFile, Long> {
}
