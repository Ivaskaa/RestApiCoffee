package com.example.RestApiCoffee.dto.requests.order.products;

import com.example.RestApiCoffee.dto.requests.order.ObjectRequest;
import lombok.Data;

@Data
public class DessertOrderRequest {
    private ObjectRequest dessert;
    private ObjectRequest syrup;
    private ObjectRequest supplement;
    private ObjectRequest size;
    private Integer count;
}
