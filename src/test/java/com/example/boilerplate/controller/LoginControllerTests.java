package com.example.boilerplate.controller;

import com.example.boilerplate.advice.exceptions.UnauthorizedException;
import com.example.boilerplate.meta.annotations.ControllerTest;
import com.example.boilerplate.model.dtos.login.LoginRequest;
import com.example.boilerplate.model.dtos.login.LoginResponse;
import com.example.boilerplate.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static com.example.boilerplate.util.Commons.buildHeader;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@ControllerTest
class LoginControllerTests {
    @MockBean
    private LoginService loginService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testReturnsTokenToUserForValidCredentials() throws Exception {
        when(loginService.authenticate(new LoginRequest().setUsername("test").setPassword("test")))
                .thenReturn(new LoginResponse().setToken("12345"));

        mockMvc.perform(post("/login")
                .headers(buildHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson())
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("12345"));
    }

    @Test
    void testReturnsUnauthorizedCodeForInvalidCredentials() throws Exception {
        when(loginService.authenticate(new LoginRequest().setUsername("test").setPassword("test")))
                .thenThrow(new UnauthorizedException("Invalid Credentials"));

        mockMvc.perform(post("/login")
                .headers(buildHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson())
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testReturnsBadRequestForWrongRequest() throws Exception {
        mockMvc.perform(post("/login")
                .headers(buildHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(badLoginJson())
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private byte[] loginJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsBytes(new LoginRequest().setUsername("test").setPassword("test"));
    }

    private byte[] badLoginJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsBytes(new LoginRequest().setUsername("test"));
    }
}
