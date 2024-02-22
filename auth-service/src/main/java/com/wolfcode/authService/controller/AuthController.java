package com.wolfcode.authService.controller;

import com.wolfcode.authService.model.AuthRequest;
import com.wolfcode.authService.model.AuthResponse;
import com.wolfcode.authService.model.RegisterRequest;
import com.wolfcode.authService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest){

        return authService.login(authRequest);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {

        authService.register(registerRequest);
        //redirect user to login after register
    }


}
