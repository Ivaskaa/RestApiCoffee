package com.example.RestApiCoffee.dto.requests.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {
    @NotBlank(message = "Must not be empty")
    @ApiModelProperty(notes = "User phone number", example = "0973423583", required = true)
    private String phone;
    @NotBlank(message = "Must not be empty")
    @ApiModelProperty(notes = "User phone number", example = "name", required = true)
    private String name;
    @NotBlank(message = "Must not be empty")
    @Size(min = 8, max = 255, message = "Must be rather then 8 and less then 20 symbols")
    @ApiModelProperty(notes = "User phone number", example = "password", required = true)
    private String password;
}
