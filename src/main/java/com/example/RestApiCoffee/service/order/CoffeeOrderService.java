package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@AllArgsConstructor
public class CoffeeOrderService {
    public Double getPrice(CoffeeOrder coffeeOrder) {
        double price = 0;
        price += coffeeOrder.getSize().getPrice();
        if(coffeeOrder.getAlcohol() != null){
            price += coffeeOrder.getAlcohol().getPrice();
        }
        if(coffeeOrder.getSyrup() != null){
            price += coffeeOrder.getSyrup().getPrice();
        }
        if(coffeeOrder.getMilk() != null){
            price += coffeeOrder.getMilk().getPrice();
        }
        if(coffeeOrder.getSupplement() != null){
            price += coffeeOrder.getSupplement().getPrice();
        }
        price *= coffeeOrder.getCount();
        return price;
    }
}
