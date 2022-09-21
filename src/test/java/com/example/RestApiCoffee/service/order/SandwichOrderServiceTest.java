package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.others.IngredientsFactory;
import com.example.RestApiCoffee.others.OrderFactory;
import com.example.RestApiCoffee.others.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class SandwichOrderServiceTest {
    @Autowired
    private SandwichOrderService sandwichOrderService;

    @Test
    void getPrice() {
        ProductFactory productFactory = new ProductFactory();
        IngredientsFactory ingredientsFactory = new IngredientsFactory();
        OrderFactory orderFactory = new OrderFactory(productFactory, ingredientsFactory);
        SandwichOrder sandwichOrder = orderFactory.getSandwichOrder();

        Double price = sandwichOrderService.getPrice(sandwichOrder);
        Assertions.assertEquals(price, 3d);
    }
}