package com.pe.unieventia.student.dto.response;

import lombok.Data;

@Data
public class EmailResumeResponseDTO {
    private Long emailId;
    private String local;
    private String domain;
}
