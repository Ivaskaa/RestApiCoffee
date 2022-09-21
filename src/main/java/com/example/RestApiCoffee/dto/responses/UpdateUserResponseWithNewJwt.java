package com.example.RestApiCoffee.dto.responses;

import com.example.RestApiCoffee.entities.user.User;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserResponseWithNewJwt {
    private Long id;
    private String phone;
    private String name;
    private String newPassword;
    private Date birthday;
    private String token;
    private String type = "Bearer";
}
