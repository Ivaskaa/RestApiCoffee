package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import com.example.RestApiCoffee.others.IngredientsFactory;
import com.example.RestApiCoffee.others.OrderFactory;
import com.example.RestApiCoffee.others.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class SnackOrderServiceTest {
    @Autowired
    private SnackOrderService snackOrderService;

    @Test
    void getPrice() {
        ProductFactory productFactory = new ProductFactory();
        IngredientsFactory ingredientsFactory = new IngredientsFactory();
        OrderFactory orderFactory = new OrderFactory(productFactory, ingredientsFactory);
        SnackOrder snackOrder = orderFactory.getSnackOrder();

        Double price = snackOrderService.getPrice(snackOrder);
        Assertions.assertEquals(price, 3d);
    }
}