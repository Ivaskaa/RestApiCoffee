package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class DessertOrderService {

    public Double getPrice(DessertOrder dessertOrder) {
        double price = 0;
        price += dessertOrder.getSize().getPrice();
        if(dessertOrder.getSyrup() != null){
            price += dessertOrder.getSyrup().getPrice();
        }
        if(dessertOrder.getSupplement() != null){
            price += dessertOrder.getSupplement().getPrice();
        }
        price *= dessertOrder.getCount();
        return price;
    }

}
