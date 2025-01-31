package com.spring.boilerplate.spring_boilerplate.auth.controller;

import com.spring.boilerplate.spring_boilerplate.auth.dto.request.LoginRequestDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.request.RegisterRequestDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.response.LoginResponseDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.response.RegisterResponseDto;
import com.spring.boilerplate.spring_boilerplate.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> testing() {
        return ResponseEntity.ok("User Service, Ready!");
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(userService.register(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.signin(loginRequestDto));
    }
}
