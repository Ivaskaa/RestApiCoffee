package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.entities.product.dessert.Dessert;
import com.example.RestApiCoffee.repository.product.CoffeeRepository;
import com.example.RestApiCoffee.repository.product.DessertRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DessertServiceTest {
    @Autowired
    private DessertService dessertService;
    @MockBean
    private DessertRepository dessertRepository;

    @Test
    void findAllActive() {
        List<Dessert> products = dessertService.findAllActive();
        Assertions.assertNotNull(products);
        Mockito.verify(dessertRepository, Mockito.times(1)).findAllActive();
    }
}