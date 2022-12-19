package com.example.RestApiCoffee.dto.requests.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordRequest {
    @NotBlank(message = "Must not be empty")
    @Size(min = 8, max = 255, message = "Must be rather then 8 and less then 255 symbols")
    @ApiModelProperty(notes = "User password", example = "password", required = true)
    private String password;
}
