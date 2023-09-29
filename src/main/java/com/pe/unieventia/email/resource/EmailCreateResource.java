package com.pe.unieventia.email.resource;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailCreateResource {
    @Email
    private String email;
}
