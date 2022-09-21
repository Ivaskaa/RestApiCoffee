package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.sauce.Sauce;
import com.example.RestApiCoffee.repository.ingredients.SauceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class SauceServiceTest {
    @Autowired
    private SauceService sauceService;
    @MockBean
    private SauceRepository sauceRepository;

    @Test
    void findAllActive() {
        List<Sauce> ingredients = sauceService.findAllActive();
        Assertions.assertNotNull(ingredients);
        Mockito.verify(sauceRepository, Mockito.times(1)).findAllActive();
    }
}