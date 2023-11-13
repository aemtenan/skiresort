package com.example.skiresort.repository;

import com.example.skiresort.model.Resort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResortRepository extends JpaRepository<Resort, Long> {
}
