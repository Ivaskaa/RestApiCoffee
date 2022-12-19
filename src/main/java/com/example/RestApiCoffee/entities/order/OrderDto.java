package com.example.RestApiCoffee.entities.order;

import com.example.RestApiCoffee.entities.PaymentForm;
import com.example.RestApiCoffee.entities.city.City;
import com.example.RestApiCoffee.entities.city.CityDto;
import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrderDto;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrderDto;
import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrderDto;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrderDto;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrderDto;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDto {
    private Date date;
    private Date time;
    private Set<CoffeeOrderDto> coffeeOrder;
    private Set<TeaOrderDto> teaOrder;
    private Set<DessertOrderDto> dessertOrder;
    private Set<SandwichOrderDto> sandwichOrder;
    private Set<SnackOrderDto> snackOrder;
    private CityDto city;
    private String home;
    private String entrance;
    private String flat;
    private PaymentForm paymentForm;
    private String prepareCash;
    private String comment;

    public Order build(){
        Order order = new Order();
        order.setDate(date);
        order.setTime(time);

        order.setCoffeeOrder(new HashSet<>());
        if(coffeeOrder != null) {
            for (CoffeeOrderDto coffee : coffeeOrder) {
                order.getCoffeeOrder().add(coffee.build());
            }
        }
        if(teaOrder != null) {
            order.setTeaOrder(new HashSet<>());
            for (TeaOrderDto tea : teaOrder) {
                order.getTeaOrder().add(tea.build());
            }
        }
        if(dessertOrder != null) {
            order.setDessertOrder(new HashSet<>());
            for (DessertOrderDto dessert : dessertOrder) {
                order.getDessertOrder().add(dessert.build());
            }
        }
        if(sandwichOrder != null) {
            order.setSandwichOrder(new HashSet<>());
            for (SandwichOrderDto sandwich : sandwichOrder) {
                order.getSandwichOrder().add(sandwich.build());
            }
        }
        if(snackOrder != null) {
            order.setSnackOrder(new HashSet<>());
            for (SnackOrderDto snack : snackOrder) {
                order.getSnackOrder().add(snack.build());
            }
        }
        order.setCity(this.city.build());
        order.setHome(this.home);
        order.setEntrance(this.entrance);
        order.setFlat(this.flat);
        order.setPaymentForm(this.paymentForm);
        order.setPrepareCash(this.prepareCash);
        order.setComment(this.comment);
        return order;
    }

}
