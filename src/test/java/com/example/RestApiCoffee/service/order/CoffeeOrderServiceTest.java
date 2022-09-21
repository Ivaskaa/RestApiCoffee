package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.others.IngredientsFactory;
import com.example.RestApiCoffee.others.OrderFactory;
import com.example.RestApiCoffee.others.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class CoffeeOrderServiceTest {
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Test
    void getPrice() {
        ProductFactory productFactory = new ProductFactory();
        IngredientsFactory ingredientsFactory = new IngredientsFactory();
        OrderFactory orderFactory = new OrderFactory(productFactory, ingredientsFactory);
        CoffeeOrder coffeeOrder = orderFactory.getCoffeeOrder();

        Double price = coffeeOrderService.getPrice(coffeeOrder);
        Assertions.assertEquals(price, 5d);
    }
}