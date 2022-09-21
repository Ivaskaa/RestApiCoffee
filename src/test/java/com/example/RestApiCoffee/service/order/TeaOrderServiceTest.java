package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import com.example.RestApiCoffee.others.IngredientsFactory;
import com.example.RestApiCoffee.others.OrderFactory;
import com.example.RestApiCoffee.others.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeaOrderServiceTest {
    @Autowired
    private TeaOrderService teaOrderService;

    @Test
    void getPrice() {
        ProductFactory productFactory = new ProductFactory();
        IngredientsFactory ingredientsFactory = new IngredientsFactory();
        OrderFactory orderFactory = new OrderFactory(productFactory, ingredientsFactory);
        TeaOrder teaOrder = orderFactory.getTeaOrder();

        Double price = teaOrderService.getPrice(teaOrder);
        Assertions.assertEquals(price, 4d);
    }
}