package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.repository.ingredients.SyrupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
@SpringBootTest
class SyrupServiceTest {
    @Autowired
    private SyrupService syrupService;
    @MockBean
    private SyrupRepository syrupRepository;

    @Test
    void findAllActive() {
        List<Syrup> ingredients = syrupService.findAllActive();
        Assertions.assertNotNull(ingredients);
        Mockito.verify(syrupRepository, Mockito.times(1)).findAllActive();
    }
}