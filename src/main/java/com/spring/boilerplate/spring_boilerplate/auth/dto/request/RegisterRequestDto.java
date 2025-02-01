package com.spring.boilerplate.spring_boilerplate.auth.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterRequestDto(
        @NotNull(message = "username is required")
        String username,
        @NotNull(message = "password is required")
        String password,
        @NotNull(message = "email is required")
        String email,
        @NotNull(message = "phone is required")
        String phone,
        @NotNull(message = "address is required")
        String address,
        @NotNull(message = "firstname is required")
        String firstname,
        @NotNull(message = "lastname is required")
        String lastname,
        List<String> roles
) {}
