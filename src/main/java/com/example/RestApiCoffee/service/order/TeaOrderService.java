package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class TeaOrderService {

    public Double getPrice(TeaOrder teaOrder) {
        double price = 0;
        price += teaOrder.getSize().getPrice();
        if(teaOrder.getSyrup() != null){
            price += teaOrder.getSyrup().getPrice();
        }
        if(teaOrder.getMilk() != null){
            price += teaOrder.getMilk().getPrice();
        }
        if(teaOrder.getSupplement() != null){
            price += teaOrder.getSupplement().getPrice();
        }
        price *= teaOrder.getCount();
        return price;
    }
}
