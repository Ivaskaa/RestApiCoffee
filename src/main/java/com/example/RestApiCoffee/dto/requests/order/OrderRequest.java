package com.example.RestApiCoffee.dto.requests.order;

import com.example.RestApiCoffee.dto.requests.order.products.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class OrderRequest {
    private Date date;
    private Date time;
    private ObjectRequest city;
    private Set<CoffeeOrderRequest> coffeeOrders;
    private Set<DessertOrderRequest> dessertOrders;
    private Set<TeaOrderRequest> teaOrders;
    private Set<SandwichOrderRequest> sandwichOrders;
    private Set<SnackOrderRequest> snackOrders;
    private String home;
    private String entrance;
    private String flat;
    private ObjectRequest paymentForm;
    private String prepareCash;
    private String comment;
}
