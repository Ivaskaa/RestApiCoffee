package com.example.RestApiCoffee.dto.requests.order.products;

import com.example.RestApiCoffee.dto.requests.order.ObjectRequest;
import lombok.*;

@Data
public class SnackOrderRequest {
    private ObjectRequest snack;
    private ObjectRequest sauce;
    private ObjectRequest supplement;
    private ObjectRequest size;
    private Integer count;
}


