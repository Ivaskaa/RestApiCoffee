package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.sauce.Sauce;
import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.repository.ingredients.SauceRepository;
import com.example.RestApiCoffee.repository.ingredients.SupplementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupplementServiceTest {
    @Autowired
    private SupplementService supplementService;
    @MockBean
    private SupplementRepository supplementRepository;

    @Test
    void findAllActive() {
        List<Supplement> ingredients = supplementService.findAllActive();
        Assertions.assertNotNull(ingredients);
        Mockito.verify(supplementRepository, Mockito.times(1)).findAllActive();
    }
}