package com.pe.unieventia.student.dto;

import lombok.Data;

@Data
public class EmailResponseDTO {
    private Long emailId;
    private String local;
    private String domain;
    private UniversityResponseDTO university;
}
