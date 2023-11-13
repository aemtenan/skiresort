package com.example.skiresort.repository;

import com.example.skiresort.model.Accommodation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findByResortId(Long resortId);

    @Transactional
    void deleteByResortId(long resortId);
}
