package com.spring.boilerplate.spring_boilerplate.auth.dto.response;

import com.spring.boilerplate.spring_boilerplate.auth.models.entity.Role;

import java.util.List;

public record DetailProfileDto(
        String id,
        String username,
        String password,
        String phone,
        String address,
        String firstname,
        String lastname,
        List<Role> roles
) {}
