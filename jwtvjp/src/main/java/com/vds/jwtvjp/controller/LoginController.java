package com.vds.jwtvjp.controller;

import com.vds.jwtvjp.entity.CustomUserDetails;
import com.vds.jwtvjp.payload.ApiResponse;
import com.vds.jwtvjp.payload.LoginRequest;
import com.vds.jwtvjp.payload.LoginResponse;
import com.vds.jwtvjp.util.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public LoginController(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return ResponseEntity.ok(new ApiResponse<>(true, new LoginResponse(token)));
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.ok(new ApiResponse<>(false, "Authenticate fail"));
        }
    }
}
