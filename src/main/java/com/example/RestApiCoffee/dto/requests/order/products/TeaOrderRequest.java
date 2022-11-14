package com.example.RestApiCoffee.dto.requests.order.products;

import com.example.RestApiCoffee.dto.requests.order.ObjectRequest;
import lombok.Data;

@Data
public class TeaOrderRequest {
    private ObjectRequest tea;
    private ObjectRequest syrup;
    private ObjectRequest milk;
    private ObjectRequest supplement;
    private ObjectRequest size;
    private Integer count;
}
