package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.repository.ingredients.AlcoholRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlcoholServiceTest {
    @Autowired
    private AlcoholService alcoholService;
    @MockBean
    private AlcoholRepository alcoholRepository;

    @Test
    void findAllActive() {
        List<Alcohol> ingredients = alcoholService.findAllActive();
        Assertions.assertNotNull(ingredients);
        Mockito.verify(alcoholRepository, Mockito.times(1)).findAllActive();
    }
}