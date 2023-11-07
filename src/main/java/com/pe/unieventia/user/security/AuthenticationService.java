package com.pe.unieventia.user.security;


import com.pe.unieventia.student.domain.service.EmailService;
import com.pe.unieventia.user.domain.entity.Role;
import com.pe.unieventia.user.domain.entity.User;
import com.pe.unieventia.user.domain.persistence.UserRepository;
import com.pe.unieventia.user.dto.JwtAuthenticationResponseDTO;
import com.pe.unieventia.user.dto.SignInRequestDTO;
import com.pe.unieventia.user.dto.SignUpRequestDTO;
import com.pe.unieventia.user.dto.UserDTO;
import com.pe.unieventia.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    private final EmailService emailService;
    private final JwtService jwtService;
    @Transactional
    public UserDTO signup(SignUpRequestDTO request) {
        var user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(emailService.createEmail(request.getEmailAddress()))
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .build();
        userRepository.save(user);
        return  userMapper.mapToDTO(user);
    }


    public JwtAuthenticationResponseDTO signin(SignInRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailAddress(),
                        request.getPassword()
                )
        );
        String[] emailParts = request.getEmailAddress().split("@");
        var user = userRepository
                .findByEmail_LocalAndEmail_EmailDomain_Domain(emailParts[0], emailParts[1])
                .orElseThrow(() -> new IllegalArgumentException("Email incorrect."));

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDTO
                .builder()
                .token(jwt)
                .userDto(userMapper.mapToDTO(user))
                .build();
    }
}