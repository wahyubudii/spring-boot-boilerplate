package com.spring.boilerplate.spring_boilerplate.auth.models.repository;

import com.spring.boilerplate.spring_boilerplate.auth.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String username);

    boolean existsByPhone(String username);
}
