package com.pe.unieventia.email.resource;

import lombok.Data;

@Data
public class EmailResponseResource {
    private Long emailId;
    private String local;
    private Long emailDomainId;
}
