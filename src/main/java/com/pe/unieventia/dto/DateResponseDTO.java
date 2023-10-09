package com.pe.unieventia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateResponseDTO {
    private Long id;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
}
