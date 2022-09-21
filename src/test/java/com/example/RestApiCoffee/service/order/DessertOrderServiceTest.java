package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.others.IngredientsFactory;
import com.example.RestApiCoffee.others.OrderFactory;
import com.example.RestApiCoffee.others.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class DessertOrderServiceTest {
    @Autowired
    private DessertOrderService dessertOrderService;

    @Test
    void getPrice() {
        ProductFactory productFactory = new ProductFactory();
        IngredientsFactory ingredientsFactory = new IngredientsFactory();
        OrderFactory orderFactory = new OrderFactory(productFactory, ingredientsFactory);
        DessertOrder dessertOrder = orderFactory.getDessertOrder();

        Double price = dessertOrderService.getPrice(dessertOrder);
        Assertions.assertEquals(price, 3d);
    }
}