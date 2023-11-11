package com.pe.unieventia.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pe.unieventia.security.domain.entity.GoogleInfo;
import com.pe.unieventia.security.domain.entity.Role;
import com.pe.unieventia.security.domain.entity.User;
import com.pe.unieventia.security.domain.persistence.UserRepository;
import com.pe.unieventia.security.domain.service.AuthenticationService;
import com.pe.unieventia.security.domain.service.GoogleInfoService;
import com.pe.unieventia.security.domain.service.JwtService;
import com.pe.unieventia.security.dto.JwtAuthenticationResponseDTO;
import com.pe.unieventia.security.dto.SignInRequestDTO;
import com.pe.unieventia.security.dto.SignUpRequestDTO;
import com.pe.unieventia.security.dto.UserResponseDTO;
import com.pe.unieventia.security.mapper.UserMapper;
import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ValidationException;
import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.EmailDomain;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.entity.University;
import com.pe.unieventia.student.domain.service.EmailService;
import com.pe.unieventia.student.domain.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserMapper userMapper;
    @Mock
    private GoogleInfoService googleInfoService;
    @Mock
    private EmailService emailService;
    @Mock
    private StudentService studentService;
    @Mock
    private JwtService jwtService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUp() {
        // Arrange
        SignUpRequestDTO request = SignUpRequestDTO.builder()
                .surname("Luca")
                .firstName("Luis")
                .lastName("Luna")
                .studentCode("u202231451")
                .emailAddress("u202231451@upc.edu.pe")
                .password("alpha1")
                .build();

        University university = University.builder()
                .name("Universidad Peruana de Ciencias Aplicadas")
                .abbreviation("UPC")
                .build();
        EmailDomain emailDomain = EmailDomain.builder()
                .domain("upc.edu.pe")
                .university(university)
                .build();

        // Process

        GoogleInfo googleInfo = GoogleInfo.builder()
                .accessToken("0")
                .refreshToken("0")
                .build();

        when(googleInfoService.createDefault())
                .thenReturn(googleInfo);

        Email email = Email.builder()
                .local("u202231451")
                .emailDomain(emailDomain)
                .build();
        LocalDateTime creationDateTime = LocalDateTime.now();
        Role role = Role.STUDENT;

        when(emailService.createEmail(request.getEmailAddress()))
                .thenReturn(email);
        when(passwordEncoder.encode(request.getPassword()))
                .thenReturn("$2a$10$TnzJ/gUfUYaWuMrZX8OBY.5ZGRa9AWKSTsTvCjL94mwzaJy7E8d6u");

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(email)
                .password(request.getPassword())
                .googleInfo(googleInfo)
                .creationDateTime(creationDateTime)
                .role(role)
                .build();

        when(userRepository.save(user)).thenReturn(user);

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .userId(1L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .role(role.name())
                .build();

        when(userMapper.entityToResponseDto(user))
                .thenReturn(userResponseDTO);

        Student student = Student.builder()
                .user(user)
                .surname(request.getSurname())
                .studentCode(request.getStudentCode())
                .build();

        when(studentService.signUp(user, request.getSurname(), request.getStudentCode()))
                .thenReturn(student);

        // Act

        UserResponseDTO result = authenticationService.signUp(request);
        //assertEquals(userResponseDTO, result);
    }

    @Test
    public void testSignUpEmailAlreadyRegistered() {
        // Arrange
        SignUpRequestDTO request = SignUpRequestDTO.builder()
                .surname("Luca")
                .firstName("Luis")
                .lastName("Luna")
                .studentCode("u202231451")
                .emailAddress("u202231451@upc.edu.pe")
                .password("alpha1")
                .build();

        University university = University.builder()
                .name("Universidad Peruana de Ciencias Aplicadas")
                .abbreviation("UPC")
                .build();
        EmailDomain emailDomain = EmailDomain.builder()
                .domain("upc.edu.pe")
                .university(university)
                .build();

        // Process
        String[] emailParts = request.getEmailAddress().split("@");
        when(emailService.createEmail(request.getEmailAddress()))
                .thenThrow(new ResourceAlreadyExistsException("Email " + request.getEmailAddress() + " is already registered"));

        // Act
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            authenticationService.signUp(request);
        });

    }

    @Test
    public void testSignUpInvalidEmailDomain() {
        // Arrange
        SignUpRequestDTO request = SignUpRequestDTO.builder()
                .surname("Luca")
                .firstName("Luis")
                .lastName("Luna")
                .studentCode("u202231451")
                .emailAddress("u202231451@upc.edu.pe")
                .password("alpha1")
                .build();

        University university = University.builder()
                .name("Universidad Peruana de Ciencias Aplicadas")
                .abbreviation("UPC")
                .build();
        EmailDomain emailDomain = EmailDomain.builder()
                .domain("upc.edu.pe")
                .university(university)
                .build();

        // Process
        String[] emailParts = request.getEmailAddress().split("@");
        when(emailService.createEmail(request.getEmailAddress()))
                .thenThrow(new ValidationException("Email domain " + emailParts[1] + " is not a valid domain"));

        // Act
        assertThrows(ValidationException.class, () -> {
            authenticationService.signUp(request);
        });

    }

    @Test
    public void TestSignIn() {
        // Arrange
        SignInRequestDTO request = SignInRequestDTO.builder()
                .emailAddress("u202231451@upc.edu.pe")
                .password("alpha1")
                .build();

        var university = University.builder()
                .name("Universidad Peruana de Ciencias Aplicadas")
                .abbreviation("UPC")
                .build();
        var emailDomain = EmailDomain.builder()
                .domain("upc.edu.pe")
                .university(university)
                .build();
        var email = Email.builder()
                .local("u202231451")
                .emailDomain(emailDomain)
                .build();
        var creationDateTime = LocalDateTime.now();
        var role = Role.STUDENT;
        var googleInfo = GoogleInfo.builder()
                .accessToken("0")
                .refreshToken("0")
                .build();
        var user = User.builder()
                .firstName("Luis")
                .lastName("Luna")
                .email(email)
                .password("alpha1")
                .googleInfo(googleInfo)
                .creationDateTime(creationDateTime)
                .role(role)
                .build();

        // Process
        when(authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmailAddress(),
                                request.getPassword()
                        )
                )
        ).thenReturn(mock(Authentication.class));

        String[] emailParts = request.getEmailAddress().split("@");
        when(userRepository.findByEmail_LocalAndEmail_EmailDomain_Domain(emailParts[0], emailParts[1]))
                .thenReturn(Optional.of(user));

        Map<String, Object> extraClaims = Map.of(
                "role", user.getRole()
        );
        var jwt = "asdasdasd";
        when(jwtService.generateToken(extraClaims, user)).thenReturn(jwt);

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .userId(1L)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmail().getEmailAddress())
                .role(role.name())
                .build();

        when(userMapper.entityToResponseDto(user)).thenReturn(userResponseDTO);

        var jwtAuthenticationResponseDTO = JwtAuthenticationResponseDTO.builder()
                .token(jwt)
                .userDto(userResponseDTO)
                .build();
        // Act
        var result = authenticationService.signIn(request);
        assertEquals(jwtAuthenticationResponseDTO, result);
    }

    @Test
    public void TestSignUserNotExist() {
        SignInRequestDTO request = SignInRequestDTO.builder()
                .emailAddress("u202231451@upc.edu.pe")
                .password("alpha11")
                .build();

        var university = University.builder()
                .name("Universidad Peruana de Ciencias Aplicadas")
                .abbreviation("UPC")
                .build();
        var emailDomain = EmailDomain.builder()
                .domain("upc.edu.pe")
                .university(university)
                .build();
        var email = Email.builder()
                .local("u202231451")
                .emailDomain(emailDomain)
                .build();
        var creationDateTime = LocalDateTime.now();
        var role = Role.STUDENT;
        var googleInfo = GoogleInfo.builder()
                .accessToken("0")
                .refreshToken("0")
                .build();
        var user = User.builder()
                .firstName("Luis")
                .lastName("Luna")
                .email(email)
                .password("alpha1")
                .googleInfo(googleInfo)
                .creationDateTime(creationDateTime)
                .role(role)
                .build();

        when(authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmailAddress(),
                                request.getPassword()
                        )
                )
        ).thenReturn(mock(Authentication.class));

        String[] emailParts = request.getEmailAddress().split("@");
        when(userRepository.findByEmail_LocalAndEmail_EmailDomain_Domain(emailParts[0], emailParts[1]))
                .thenThrow(new IllegalArgumentException("Email " + request.getEmailAddress() + " is incorrect."));

        // Act
        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.signIn(request);
        });
    }
}
