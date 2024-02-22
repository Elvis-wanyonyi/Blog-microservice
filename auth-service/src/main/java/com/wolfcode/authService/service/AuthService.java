package com.wolfcode.authService.service;

import com.wolfcode.authService.config.JwtService;
import com.wolfcode.authService.domain.Role;
import com.wolfcode.authService.domain.User;
import com.wolfcode.authService.model.AuthRequest;
import com.wolfcode.authService.model.AuthResponse;
import com.wolfcode.authService.model.RegisterRequest;
import com.wolfcode.authService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(AuthRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void register(RegisterRequest registerRequest) {
        var user = User.builder()
                .userId(UUID.randomUUID().toString().substring(1,8).toUpperCase())
                .joinedOn(LocalDateTime.now())
                .username(registerRequest.getUsername())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        log.info("new user: {} was added",registerRequest.getEmail());

//        var jwtToken = jwtService.generateToken(user);
//        return AuthResponse.builder()
//                .token(jwtToken)
//                .build();
    }
}
