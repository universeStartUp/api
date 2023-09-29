package com.pe.unieventia.email.resource;

import jakarta.persistence.Column;
//import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class EmailResource {
    @Column(length = 64)
    private String local;
    private Long emailDomainId;
}
