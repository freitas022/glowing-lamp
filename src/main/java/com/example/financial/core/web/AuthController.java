package com.example.financial.core.web;

import com.example.financial.core.dto.LoginRequest;
import com.example.financial.core.service.AuthService;
import com.example.financial.core.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    public record LoginResponse(String accessToken) {
    }

    @PostMapping("/accessToken")
    public LoginResponse authenticate(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authService.authenticate(request);
        String token = tokenService.generateAccessToken(authentication);
        return new LoginResponse(token);
    }
}
