package com.example.RestApiCoffee.dto.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Award {
    @NotNull(message = "Must not be empty")
    private long id;
}
