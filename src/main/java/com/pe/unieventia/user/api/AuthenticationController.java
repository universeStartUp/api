package com.pe.unieventia.user.api;

import com.pe.unieventia.user.dto.JwtAuthenticationResponseDTO;
import com.pe.unieventia.user.dto.SignInRequestDTO;
import com.pe.unieventia.user.dto.SignUpRequestDTO;
import com.pe.unieventia.user.dto.UserDTO;
import com.pe.unieventia.user.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignUpRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDTO> signin(@RequestBody SignInRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}