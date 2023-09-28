package com.pe.unieventia.email.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailResource {
    private String local;
    private Long emailDomainId;
}
