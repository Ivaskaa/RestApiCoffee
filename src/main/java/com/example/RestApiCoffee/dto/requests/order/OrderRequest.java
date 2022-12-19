package com.example.RestApiCoffee.dto.requests.order;

import com.example.RestApiCoffee.dto.requests.order.products.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
public class OrderRequest {
    @NotNull(message = "Must not be empty")
    @JsonFormat(pattern="dd.MM.yyyy")
    @ApiModelProperty(notes = "Date", example = "30.12.2022")
    private Date date;
    @NotNull(message = "Must not be empty")
    @JsonFormat(pattern="hh:mm")
    @ApiModelProperty(notes = "Time", example = "09:59")
    private Date time;
    @NotNull(message = "Must not be empty")
    @ApiModelProperty(notes = "City id", example = "1")
    private ObjectRequest city;
    @ApiModelProperty(notes = "Coffee order")
    private Set<CoffeeOrderRequest> coffeeOrders;
    @ApiModelProperty(notes = "Dessert order")
    private Set<DessertOrderRequest> dessertOrders;
    @ApiModelProperty(notes = "Tea order")
    private Set<TeaOrderRequest> teaOrders;
    @ApiModelProperty(notes = "Sandwich order")
    private Set<SandwichOrderRequest> sandwichOrders;
    @ApiModelProperty(notes = "Snack order")
    private Set<SnackOrderRequest> snackOrders;
    @NotBlank(message = "Must not be empty")
    @ApiModelProperty(notes = "Home", example = "street and number home")
    private String home;
    @ApiModelProperty(notes = "Home", example = "entrance")
    private String entrance;
    @ApiModelProperty(notes = "Home", example = "flat")
    private String flat;
    @NotNull(message = "Must not be empty")
    @ApiModelProperty(notes = "Payment form id", example = "1")
    private ObjectRequest paymentForm;
    @ApiModelProperty(notes = "Prepare cash", example = "500.00")
    private String prepareCash;
    @ApiModelProperty(notes = "Order comment", example = "I don't have a bill less than 500 UAH")
    private String comment;
}
