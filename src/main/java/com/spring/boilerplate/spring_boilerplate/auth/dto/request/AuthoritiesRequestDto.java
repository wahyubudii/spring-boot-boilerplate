package com.spring.boilerplate.spring_boilerplate.auth.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AuthoritiesRequestDto(
        @NotNull(message = "roles is required")
        List<String> roles
) {}
