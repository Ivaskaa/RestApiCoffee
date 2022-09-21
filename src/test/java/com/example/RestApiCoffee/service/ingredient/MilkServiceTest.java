package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.repository.ingredients.AlcoholRepository;
import com.example.RestApiCoffee.repository.ingredients.MilkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MilkServiceTest {
    @Autowired
    private MilkService milkService;
    @MockBean
    private MilkRepository milkRepository;

    @Test
    void findAllActive() {
        List<Milk> ingredients = milkService.findAllActive();
        Assertions.assertNotNull(ingredients);
        Mockito.verify(milkRepository, Mockito.times(1)).findAllActive();
    }
}