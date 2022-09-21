package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.repository.ingredients.SyrupRepository;
import com.example.RestApiCoffee.repository.product.CoffeeRepository;
import com.example.RestApiCoffee.service.ingredient.SyrupService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CoffeeServiceTest {
    @Autowired
    private CoffeeService coffeeService;
    @MockBean
    private CoffeeRepository coffeeRepository;

    @Test
    void findAllActive() {
        List<Coffee> products = coffeeService.findAllActive();
        Assertions.assertNotNull(products);
        Mockito.verify(coffeeRepository, Mockito.times(1)).findAllActive();
    }
}