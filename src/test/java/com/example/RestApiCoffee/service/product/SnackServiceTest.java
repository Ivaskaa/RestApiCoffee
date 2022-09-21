package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.repository.product.SnackRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
@SpringBootTest
class SnackServiceTest {
    @Autowired
    private SnackService snackService;
    @MockBean
    private SnackRepository snackRepository;
    @Test
    void findAllActive() {
        List<Snack> products = snackService.findAllActive();
        Assertions.assertNotNull(products);
        Mockito.verify(snackRepository, Mockito.times(1)).findAllActive();
    }
}