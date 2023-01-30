package com.example.boilerplate.service;

import com.example.boilerplate.model.dtos.login.LoginRequest;
import com.example.boilerplate.model.dtos.login.LoginResponse;

public interface LoginService {
    LoginResponse authenticate(LoginRequest loginRequest);
}
