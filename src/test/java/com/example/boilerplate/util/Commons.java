package com.example.boilerplate.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Commons {
    public static HttpHeaders buildHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjUwMjIzOTUxLCJpYXQiOjE2NTAyMDU5NTF9.dA711EpuGTewUzW-lymaE8Ko367mL8ZKCX9-KsCdLLYbbqd8VsJWdBaA7gDBvYDkm9lnpcOm7iOUoneQ76sUmQ");
        return httpHeaders;
    }
}
