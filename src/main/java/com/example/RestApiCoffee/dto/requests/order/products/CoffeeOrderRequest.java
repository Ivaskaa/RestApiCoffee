package com.example.RestApiCoffee.dto.requests.order.products;

import com.example.RestApiCoffee.dto.requests.order.ObjectRequest;
import lombok.Data;

@Data
public class CoffeeOrderRequest {
    private ObjectRequest coffee;
    private ObjectRequest syrup;
    private ObjectRequest alcohol;
    private ObjectRequest milk;
    private ObjectRequest supplement;
    private ObjectRequest size;
    private Integer count;
}
