package com.example.financial.core.service;

import com.example.financial.core.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    public Authentication authenticate(LoginRequest request) {
        var credentials = accountService.getCredentialsByNumber(request.number());
        var passwordHash = credentials.getPasswordHash();

        if (!passwordEncoder.matches(request.password(), passwordHash)) {
            throw new BadCredentialsException("Senha incorreta.");
        }

        var authority = new SimpleGrantedAuthority(
                "ROLE_" + credentials.getRole()
        );

        return UsernamePasswordAuthenticationToken.authenticated(
                credentials.getCustomerId(),
                null,
                List.of(authority)
        );
    }
}
