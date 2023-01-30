package com.example.boilerplate.model.dtos.login;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class LoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
