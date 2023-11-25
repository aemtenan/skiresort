package com.example.skiresort.repository;

import com.example.skiresort.model.Resort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResortRepository extends JpaRepository<Resort, Long> {

    Page<Resort> findByTownContaining(String town, Pageable pageable);

    List<Resort> findByTownContaining(String town, Sort sort);

}
