package com.pe.unieventia.security.api;

import com.pe.unieventia.security.dto.JwtAuthenticationResponseDTO;
import com.pe.unieventia.security.dto.SignInRequestDTO;
import com.pe.unieventia.security.dto.SignUpRequestDTO;
import com.pe.unieventia.security.dto.UserResponseDTO;
import com.pe.unieventia.security.domain.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Authentication", description = "Auhtentication management APIs")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody SignUpRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDTO> signIn(@RequestBody SignInRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}