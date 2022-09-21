package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.sandwich.Sandwich;
import com.example.RestApiCoffee.repository.product.SandwichRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class SandwichServiceTest {
    @Autowired
    private SandwichService sandwichService;
    @MockBean
    private SandwichRepository sandwichRepository;

    @Test
    void findAllActive() {
        List<Sandwich> products = sandwichService.findAllActive();
        Assertions.assertNotNull(products);
        Mockito.verify(sandwichRepository, Mockito.times(1)).findAllActive();
    }
}