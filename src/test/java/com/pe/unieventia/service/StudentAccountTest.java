package com.pe.unieventia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pe.unieventia.shared.exception.ResourceAlreadyExistsException;
import com.pe.unieventia.shared.exception.ValidationException;
import com.pe.unieventia.student.domain.entity.Email;
import com.pe.unieventia.student.domain.entity.EmailDomain;
import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.domain.entity.University;
import com.pe.unieventia.student.domain.service.StudentService;
import com.pe.unieventia.student.dto.EmailResponseDTO;
import com.pe.unieventia.student.dto.StudentResponseDTO;
import com.pe.unieventia.student.dto.UniversityResponseDTO;
import com.pe.unieventia.student_account.domain.entity.GoogleInfo;
import com.pe.unieventia.student_account.domain.entity.StudentAccount;
import com.pe.unieventia.student_account.domain.persistence.StudentAccountRepository;
import com.pe.unieventia.student_account.domain.service.GoogleInfoService;
import com.pe.unieventia.student_account.domain.service.StudentAccountService;
import com.pe.unieventia.student_account.dto.StudentAccountResponseDTO;
import com.pe.unieventia.student_account.mapper.StudentAccountMapper;

import jakarta.validation.Validation;

public class StudentAccountTest {
    /*
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

    @Test
    public void testCreateStudentAccount() {
        //Arrange
        String surname = "Mitch";
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";
        String password = "password";

        String[] parts = emailAddress.split("@");

        University university = new University();
        university.setName("Universidad Internacional");
        university.setAbbreviation("UI");
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
        studentAccount.setCreationDateTime(LocalDateTime.now());
            
        UniversityResponseDTO universityResponseDTO = new UniversityResponseDTO();
        universityResponseDTO.setUniversityId(1L);
        universityResponseDTO.setName(university.getName());
        universityResponseDTO.setAbbreviation(university.getAbbreviation());
        EmailResponseDTO emailResponseDTO = new EmailResponseDTO();
        emailResponseDTO.setEmailId(1L);
        emailResponseDTO.setLocal(email.getLocal());
        emailResponseDTO.setDomain(email.getEmailDomain().getDomain());
        emailResponseDTO.setUniversity(universityResponseDTO);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudentId(1L);
        studentResponseDTO.setSurname(student.getSurname());
        studentResponseDTO.setFirstName(student.getFirstName());
        studentResponseDTO.setLastName(student.getLastName());
        studentResponseDTO.setPhoneNumber(student.getPhoneNumber());
        studentResponseDTO.setStudentCode(student.getStudentCode());
        studentResponseDTO.setEmail(emailResponseDTO);
        StudentAccountResponseDTO studentAccountResponseDTO = new StudentAccountResponseDTO();
        studentAccountResponseDTO.setStudentAccountId(1L);
        studentAccountResponseDTO.setStudent(studentResponseDTO);
        studentAccountResponseDTO.setCreationDateTime(studentAccount.getCreationDateTime());
        
        when(studentService.createStudent(
                surname,
                firstName,
                lastName,
                studentCode,
                phoneNumber,
                emailAddress
            )
        ).thenReturn(student);
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
    }

    @Test
    public void testCreateStudentAccount() {
        //Arrange
        String surname = "Mitch";
        String firstName = "Michael";
        String lastName = "Chavez";
        String studentCode = "u202015237";
        String phoneNumber = "942675143";
        String emailAddress = "u202015237@ui.edu.com";
        String password = "password";

        String[] parts = emailAddress.split("@");

        University university = new University();
        university.setName("Universidad Internacional");
        university.setAbbreviation("UI");
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
        studentAccount.setCreationDateTime(LocalDateTime.now());
            
        UniversityResponseDTO universityResponseDTO = new UniversityResponseDTO();
        universityResponseDTO.setUniversityId(1L);
        universityResponseDTO.setName(university.getName());
        universityResponseDTO.setAbbreviation(university.getAbbreviation());
        EmailResponseDTO emailResponseDTO = new EmailResponseDTO();
        emailResponseDTO.setEmailId(1L);
        emailResponseDTO.setLocal(email.getLocal());
        emailResponseDTO.setDomain(email.getEmailDomain().getDomain());
        emailResponseDTO.setUniversity(universityResponseDTO);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudentId(1L);
        studentResponseDTO.setSurname(student.getSurname());
        studentResponseDTO.setFirstName(student.getFirstName());
        studentResponseDTO.setLastName(student.getLastName());
        studentResponseDTO.setPhoneNumber(student.getPhoneNumber());
        studentResponseDTO.setStudentCode(student.getStudentCode());
        studentResponseDTO.setEmail(emailResponseDTO);
        StudentAccountResponseDTO studentAccountResponseDTO = new StudentAccountResponseDTO();
        studentAccountResponseDTO.setStudentAccountId(1L);
        studentAccountResponseDTO.setStudent(studentResponseDTO);
        studentAccountResponseDTO.setCreationDateTime(studentAccount.getCreationDateTime());
        
        when(studentService.createStudent(
                surname,
                firstName,
                lastName,
                studentCode,
                phoneNumber,
                emailAddress
            )
        )
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
        assertThrows(
            ValidationException.class,
            () -> {
                emailService.createEmail(emailAddress);
            }
        );
    }*/
}
