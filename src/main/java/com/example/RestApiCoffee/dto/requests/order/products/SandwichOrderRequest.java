package com.example.RestApiCoffee.dto.requests.order.products;

import com.example.RestApiCoffee.dto.requests.order.ObjectRequest;
import lombok.Data;

@Data
public class SandwichOrderRequest {
    private ObjectRequest snack;
    private ObjectRequest sauce;
    private ObjectRequest supplement;
    private ObjectRequest size;
    private Integer count;
}
