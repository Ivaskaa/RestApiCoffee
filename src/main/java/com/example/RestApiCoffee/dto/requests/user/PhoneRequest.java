package com.example.RestApiCoffee.dto.requests.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PhoneRequest {
    @NotBlank(message = "Must not be empty")
    @ApiModelProperty(notes = "User phone number", example = "0973423583", required = true)
    private String phone;
}
