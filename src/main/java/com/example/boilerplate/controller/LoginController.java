package com.example.boilerplate.controller;

import com.example.boilerplate.model.dtos.login.LoginRequest;
import com.example.boilerplate.model.dtos.login.LoginResponse;
import com.example.boilerplate.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginRequest request) {
        LoginResponse loginResponse = loginService.authenticate(request);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
