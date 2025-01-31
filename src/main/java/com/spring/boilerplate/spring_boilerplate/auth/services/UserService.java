package com.spring.boilerplate.spring_boilerplate.auth.services;

import com.spring.boilerplate.spring_boilerplate.auth.dto.request.LoginRequestDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.request.RegisterRequestDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.response.LoginResponseDto;
import com.spring.boilerplate.spring_boilerplate.auth.dto.response.RegisterResponseDto;
import com.spring.boilerplate.spring_boilerplate.auth.enums.EnumUserRole;
import com.spring.boilerplate.spring_boilerplate.auth.models.entity.Role;
import com.spring.boilerplate.spring_boilerplate.auth.models.mapper.AuthMapper;
import com.spring.boilerplate.spring_boilerplate.auth.models.repository.RoleRepository;
import com.spring.boilerplate.spring_boilerplate.auth.models.repository.UserRepository;
import com.spring.boilerplate.spring_boilerplate.auth.security.jwt.JwtUtils;
import com.spring.boilerplate.spring_boilerplate.auth.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncode;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterResponseDto register(@Valid RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername(registerRequestDto.username())) {
            throw new RuntimeException("Username already exist!");
        }

        if (userRepository.existsByEmail(registerRequestDto.email())) {
            throw new RuntimeException("Email already exist!");
        }

        if (userRepository.existsByPhone(registerRequestDto.phone())) {
            throw new RuntimeException("Phone already exist!");
        }

        List<String> strRoles = registerRequestDto.role();
        List<Role> roles = new ArrayList<>();

        Role userRole = roleRepository.findByName(EnumUserRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role is not found!"));

        if (strRoles == null || strRoles.isEmpty()) {
            roles.add(userRole);
        } else {
            roles.add(userRole);
            for (String role : strRoles) {
                switch (role.toLowerCase()) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(EnumUserRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Role is not found!"));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(EnumUserRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Role is not found!"));
                        roles.add(modRole);
                        break;
                    default:
                        break;
                }
            }
        }

        var newPassword = passwordEncode.encode(registerRequestDto.password());
        var user = authMapper.registerPayload(registerRequestDto, newPassword, roles);

        userRepository.save(user);

        var response = authMapper.registerResponse(user);

        return response;
    }

    public LoginResponseDto signin(@Valid LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        var response = authMapper.loginResponse(userDetails, roles, jwt);

        return response;
    }
}
