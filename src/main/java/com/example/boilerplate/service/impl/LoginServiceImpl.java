package com.example.boilerplate.service.impl;

import com.example.boilerplate.advice.exceptions.UnauthorizedException;
import com.example.boilerplate.model.dtos.login.LoginRequest;
import com.example.boilerplate.model.dtos.login.LoginResponse;
import com.example.boilerplate.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LdapTemplate ldapTemplate;

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        try {
            ldapTemplate.authenticate(query().where("sAMAccountName").is(loginRequest.getUsername()), loginRequest.getPassword());
        } catch (Exception exception) {
            throw new UnauthorizedException("Invalid credentials");
        }
        return new LoginResponse().setToken("834riuherfiuher");
    }
}
