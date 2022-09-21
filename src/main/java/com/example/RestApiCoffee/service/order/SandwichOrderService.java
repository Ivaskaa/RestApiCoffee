package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class SandwichOrderService {

    public Double getPrice(SandwichOrder sandwichOrder) {
        double price = 0;
        price += sandwichOrder.getSize().getPrice();
        if(sandwichOrder.getSauce() != null){
            price += sandwichOrder.getSauce().getPrice();
        }
        if(sandwichOrder.getSupplement() != null){
            price += sandwichOrder.getSupplement().getPrice();
        }
        price *= sandwichOrder.getCount();
        return price;
    }
}
