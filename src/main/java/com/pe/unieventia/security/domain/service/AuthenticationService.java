package com.pe.unieventia.security.domain.service;


import com.pe.unieventia.security.domain.entity.GoogleInfo;
import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.service.EmailService;
import com.pe.unieventia.security.domain.entity.Role;
import com.pe.unieventia.security.domain.entity.User;
import com.pe.unieventia.security.domain.persistence.UserRepository;
import com.pe.unieventia.security.dto.JwtAuthenticationResponseDTO;
import com.pe.unieventia.security.dto.SignInRequestDTO;
import com.pe.unieventia.security.dto.SignUpRequestDTO;
import com.pe.unieventia.security.dto.UserResponseDTO;
import com.pe.unieventia.security.mapper.UserMapper;
import com.pe.unieventia.student.domain.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    private final GoogleInfoService googleInfoService;
    private final EmailService emailService;
    private final StudentService studentService;
    private final JwtService jwtService;
    @Transactional
    public UserResponseDTO signUp(SignUpRequestDTO request) {
        Email email = emailService.createEmail(request.getEmailAddress());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        GoogleInfo googleInfo = googleInfoService.createDefault();
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(email)
                .password(encodedPassword)
                .googleInfo(googleInfo)
                .creationDateTime(LocalDateTime.now())
                .role(Role.STUDENT)
                .build();
        userRepository.save(user);
        studentService.signUp(
                user,
                request.getSurname(),
                request.getStudentCode()
        );

        return userMapper.entityToResponseDto(user);
    }


    public JwtAuthenticationResponseDTO signIn(SignInRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailAddress(),
                        request.getPassword()
                )
        );
        String[] emailParts = request.getEmailAddress().split("@");
        var user = userRepository
                .findByEmail_LocalAndEmail_EmailDomain_Domain(emailParts[0], emailParts[1])
                .orElseThrow(() -> new IllegalArgumentException("Email " + request.getEmailAddress() + " is incorrect."));

        Map<String, Object> extraClaims = Map.of(
                "role", user.getRole()
        );
        var jwt = jwtService.generateToken(extraClaims, user);

        return JwtAuthenticationResponseDTO.builder()
                .token(jwt)
                .userDto(userMapper.entityToResponseDto(user))
                .build();
    }

}