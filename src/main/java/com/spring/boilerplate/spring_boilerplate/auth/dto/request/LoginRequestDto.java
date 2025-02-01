package com.spring.boilerplate.spring_boilerplate.auth.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull(message = "Username is required")
        String username,

        @NotNull(message = "Password is required")
        String password
) {}
