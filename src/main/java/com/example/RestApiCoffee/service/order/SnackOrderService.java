package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class SnackOrderService {

    public Double getPrice(SnackOrder snackOrder) {
        double price = 0;
        price += snackOrder.getSize().getPrice();
        if(snackOrder.getSauce() != null){
            price += snackOrder.getSauce().getPrice();
        }
        if(snackOrder.getSupplement() != null){
            price += snackOrder.getSupplement().getPrice();
        }
        price *= snackOrder.getCount();
        return price;
    }
}
