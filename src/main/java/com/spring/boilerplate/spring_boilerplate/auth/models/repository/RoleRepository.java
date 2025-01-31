package com.spring.boilerplate.spring_boilerplate.auth.models.repository;

import com.spring.boilerplate.spring_boilerplate.auth.enums.EnumUserRole;
import com.spring.boilerplate.spring_boilerplate.auth.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(EnumUserRole name);
}
