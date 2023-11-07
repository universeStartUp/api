package com.pe.unieventia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ValidationException;
import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.user.domain.persistence.StudentAccountRepository;
import com.pe.unieventia.user.domain.service.GoogleInfoService;
import com.pe.unieventia.user.domain.service.StudentAccountService;
import com.pe.unieventia.user.mapper.StudentAccountMapper;

public class StudentAccountServiceTest {
    @InjectMocks
    private StudentAccountService studentAccountService;

    @Mock
    private StudentService studentService;
    @Mock
    private GoogleInfoService googleInfoService;
    @Mock
    private StudentAccountRepository studentAccountRepository;
    @Mock
    private StudentAccountMapper studentAccountMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
                 /*
    @Test
    public void testCreateStudentAccountSuccess() {
        //Arrange
        String surname = "Mitch";
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";
        String password = "password";

        String universityName = "Universidad Internacional";
        String universityAbbreviation = "UI";

        // Process
        String[] parts = emailAddress.split("@");

        University university = new University();
        university.setName(universityName);
        university.setAbbreviation(universityAbbreviation);
        EmailDomain emailDomain = new EmailDomain();
        emailDomain.setDomain(parts[1]);
        emailDomain.setUniversity(university);
        Email email = new Email();
        email.setLocal(parts[0]);
        email.setEmailDomain(emailDomain);
        Student student = new Student();
        student.setSurname(surname);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setStudentCode(studentCode);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);


        GoogleInfo googleInfo = new GoogleInfo();
        googleInfo.setAccessToken("0");
        googleInfo.setRefreshToken("0");


        StudentAccount studentAccount = new StudentAccount();
        studentAccount.setStudent(student);
        studentAccount.setPassword(password);
        studentAccount.setGoogleInfo(googleInfo);
        LocalDateTime now = LocalDateTime.now();
        studentAccount.setCreationDateTime(now);


        UniversityResponseDTO universityResponseDTO = new UniversityResponseDTO();
        universityResponseDTO.setUniversityId(1L);
        universityResponseDTO.setName(universityName);
        universityResponseDTO.setAbbreviation(universityAbbreviation);
        EmailResponseDTO emailResponseDTO = new EmailResponseDTO();
        emailResponseDTO.setEmailId(1L);
        emailResponseDTO.setLocal(parts[0]);
        emailResponseDTO.setDomain(parts[1]);
        emailResponseDTO.setUniversity(universityResponseDTO);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudentId(1L);
        studentResponseDTO.setSurname(surname);
        studentResponseDTO.setFirstName(firstName);
        studentResponseDTO.setLastName(lastName);
        studentResponseDTO.setPhoneNumber(phoneNumber);
        studentResponseDTO.setStudentCode(studentCode);
        studentResponseDTO.setEmail(emailResponseDTO);
        StudentAccountResponseDTO studentAccountResponseDTO = new StudentAccountResponseDTO();
        studentAccountResponseDTO.setStudentAccountId(1L);
        studentAccountResponseDTO.setStudent(studentResponseDTO);
        studentAccountResponseDTO.setCreationDateTime(now);

        when(studentService.createStudent(
                surname,
                firstName,
                lastName,
                studentCode,
                phoneNumber,
                emailAddress
        )).thenReturn(student);
        when(googleInfoService.createDefault()).thenReturn(googleInfo);
        when(studentAccountRepository.save(studentAccount)).thenReturn(studentAccount);
        when(studentAccountMapper.entityToResponseDto(studentAccount)).thenReturn(studentAccountResponseDTO);

        // Act
        StudentAccountResponseDTO result = studentAccountService.createStudentAccount(
                surname,
                firstName,
                lastName,
                studentCode,
                phoneNumber,
                emailAddress,
                password
        );
        assertEquals(studentAccountResponseDTO, result);
    }*/

    @Test
    public void testCreateStudentAccountAlreadyExist() {
        //Arrange
        String surname = "Mitch";
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";
        String password = "password";

        String[] parts = emailAddress.split("@");

        when(studentService.createStudent(
                        surname,
                        firstName,
                        lastName,
                        studentCode,
                        phoneNumber,
                        emailAddress
        )).thenThrow(new ResourceAlreadyExistsException("Email " + emailAddress + " is already registered"));

        assertThrows(ResourceAlreadyExistsException.class, () -> {
            studentAccountService.createStudentAccount(
                    surname,
                    firstName,
                    lastName,
                    studentCode,
                    phoneNumber,
                    emailAddress,
                    password
            );
        });
    }

    @Test
    public void testCreateStudentAccountValidationException() {
        //Arrange
        String surname = "Mitch";
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";
        String password = "password";

        String[] parts = emailAddress.split("@");
        when(studentService.createStudent(
                        surname,
                        firstName,
                        lastName,
                        studentCode,
                        phoneNumber,
                        emailAddress
                )
        ).thenThrow(new ValidationException("Email domain " + parts[1] + " is not a valid domain"));

        assertThrows(ValidationException.class, () -> {
            studentAccountService.createStudentAccount(
                    surname,
                    firstName,
                    lastName,
                    studentCode,
                    phoneNumber,
                    emailAddress,
                    password
            );
        });
    }

}
