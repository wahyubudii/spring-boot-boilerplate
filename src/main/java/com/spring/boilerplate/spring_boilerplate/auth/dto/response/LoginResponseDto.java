package com.spring.boilerplate.spring_boilerplate.auth.dto.response;

import com.spring.boilerplate.spring_boilerplate.auth.models.entity.Role;

import java.util.List;

public record LoginResponseDto(
        String id,
        String username,
        String email,
        List<String> roles,
        String token
) {}
