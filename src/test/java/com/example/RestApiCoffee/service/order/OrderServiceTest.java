package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.others.IngredientsFactory;
import com.example.RestApiCoffee.others.OrderFactory;
import com.example.RestApiCoffee.others.ProductFactory;
import com.example.RestApiCoffee.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(new Order()))
                .when(orderRepository)
                .findById(1L);
        orderService.findById(1L);
        Mockito.verify(orderRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void save() {
        IngredientsFactory ingredientsFactory = new IngredientsFactory();
        ProductFactory productFactory = new ProductFactory();
        OrderFactory orderFactory = new OrderFactory(productFactory, ingredientsFactory);

        Order order = orderFactory.getOrder();

        Order checkedOrder = orderService.save(order);
        Assertions.assertEquals(checkedOrder, order);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    @Test
    void findByUser() {
        IngredientsFactory ingredientsFactory = new IngredientsFactory();
        ProductFactory productFactory = new ProductFactory();
        OrderFactory orderFactory = new OrderFactory(productFactory, ingredientsFactory);

        Order order = orderFactory.getOrder();
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        Mockito.doReturn(orders)
                .when(orderRepository)
                .findByUser(order.getUser());

        List<Order> checkedOrder = orderService.findByUser(order.getUser());
        Assertions.assertEquals(checkedOrder, orders);
    }
}