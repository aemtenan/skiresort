package com.example.skiresort.repository;

import com.example.skiresort.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
