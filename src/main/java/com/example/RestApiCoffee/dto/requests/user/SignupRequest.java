package com.example.RestApiCoffee.dto.requests.user;

import lombok.Data;
@Data
public class SignupRequest {
    private String phone;
    private String name;
    private String password;
}
