package com.example.boilerplate.service;

import com.example.boilerplate.meta.annotations.TestAnnotation;
import com.example.boilerplate.model.dtos.login.LoginRequest;
import com.example.boilerplate.model.dtos.login.LoginResponse;
import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.sdk.LDAPException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestAnnotation
class LoginServiceImplTests {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testAuthenticatesTheUserSuccessfully() throws LDAPException {
        LoginRequest loginRequest = new LoginRequest().setUsername("john").setPassword("secret");

        InMemoryDirectoryServer ldapServer = applicationContext.getBean(InMemoryDirectoryServer.class);
        ldapServer.restartServer();
        LoginResponse loginResponse = loginService.authenticate(loginRequest);
        assertNotNull(loginResponse.getToken());
        ldapServer.shutDown(true);
    }
}
