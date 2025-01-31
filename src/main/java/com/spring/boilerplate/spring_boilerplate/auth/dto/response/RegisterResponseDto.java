package com.spring.boilerplate.spring_boilerplate.auth.dto.response;

import com.spring.boilerplate.spring_boilerplate.auth.models.entity.Role;

import java.util.List;

public record RegisterResponseDto(
        String username,
        String password,
        String phone,
        String address,
        String firstname,
        String lastname,
        String userLastname,
        List<Role> roles
) {}
