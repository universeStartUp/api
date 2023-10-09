package com.pe.unieventia.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponseDTO {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private List<String> validationErrors;

}
