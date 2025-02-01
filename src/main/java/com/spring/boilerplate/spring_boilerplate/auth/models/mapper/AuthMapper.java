package com.spring.boilerplate.spring_boilerplate.auth.models.mapper;

import com.spring.boilerplate.spring_boilerplate.auth.dto.request.RegisterRequestDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.response.DetailProfileDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.response.LoginResponseDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.response.RegisterResponseDto;
import com.spring.boilerplate.spring_boilerplate.auth.models.entity.Role;
import com.spring.boilerplate.spring_boilerplate.auth.models.entity.User;
import com.spring.boilerplate.spring_boilerplate.auth.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthMapper {
    public User registerPayload(RegisterRequestDto registerRequestDto, String passwordEncode, List<Role> roles) {
        return User.builder()
                .username(registerRequestDto.username())
                .password(passwordEncode)
                .email(registerRequestDto.email())
                .phone(registerRequestDto.phone())
                .address(registerRequestDto.address())
                .firstname(registerRequestDto.firstname())
                .lastname(registerRequestDto.lastname())
                .roles(roles)
                .build();
    }

    public RegisterResponseDto registerResponse(User user) {
        return new RegisterResponseDto(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getFirstname(),
                user.getLastname(),
                user.getRoles()
        );
    }

    public LoginResponseDto loginResponse(UserDetailsImpl user, List<String> roles, String jwt) {
        return new LoginResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roles,
                jwt
        );
    }

    public DetailProfileDto profileResponse(User user) {
        return new DetailProfileDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getFirstname(),
                user.getLastname(),
                user.getRoles()
        );
    }
}
