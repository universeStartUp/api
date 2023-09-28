package com.pe.unieventia.student_account.domain.service;

import lombok.Data;

@Data
public class StudentAccountService {
    Long studentAccountId;
    Long studentId;
    Long emailId;
    String password;
}
